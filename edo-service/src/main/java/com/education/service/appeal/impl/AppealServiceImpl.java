package com.education.service.appeal.impl;

import com.education.author_feign.service.AuthorService;
import com.education.model.constant.RabbitConstant;
import com.education.model.dto.AppealAbbreviatedDto;
import com.education.model.dto.AppealDto;
import com.education.model.enumEntity.EnumAppealStatus;
import com.education.model.records.AppealReadRecord;
import com.education.model.util.exceptions.AppealNotValidException;
import com.education.service.appeal.AppealService;
import com.education.service.nomenclature.NomenclatureService;
import com.education.service.question.QuestionService;
import com.education.service.region.RegionService;
import com.education.service.region.impl.RegionServiceImpl;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static org.apache.logging.log4j.util.Strings.EMPTY;
import static org.springframework.util.CollectionUtils.isEmpty;
import static org.springframework.util.StringUtils.hasLength;

@Service
@RequiredArgsConstructor
public class AppealServiceImpl implements AppealService {

    private final QuestionService questionService;

    private final AuthorService authorService;
//------------------
    private final RegionService regionService;
    //----------------------
    private final RestTemplate TEMPLATE;

    private final AmqpTemplate amqpTemplate;

    private final EurekaClient EUREKA_CLIENT;

    private final NomenclatureService nomenclatureService;

    private final String BASE_URL = "/api/repository/appeal";

    private final String SERVICE_NAME = "edo-repository";

    /**
     * Получает инстанс случайным методом
     */
    private InstanceInfo getInstance() {
        List<InstanceInfo> instances = EUREKA_CLIENT.getApplication(SERVICE_NAME).getInstances();
        return instances.get((int) (instances.size() * Math.random()));
    }

    private String getURIByInstance(InstanceInfo instanceInfo, String pathVariable) {
        return UriComponentsBuilder.fromPath(BASE_URL + pathVariable)
                .scheme(HttpHost.DEFAULT_SCHEME_NAME)
                .host(instanceInfo.getHostName())
                .port(instanceInfo.getPort())
                .build().toString();
    }

    @Override
    public AppealDto save(AppealDto appealDto) {
        InstanceInfo instanceInfo = getInstance();
        String validateResult = validateAppealDto(appealDto);
        if (!validateResult.isEmpty()) {
            throw new AppealNotValidException(validateResult);
        }
        var savedAuthors = appealDto.getAuthors()
                .stream().map(authorService::save)
                .map(ResponseEntity::getBody).toList();
        var savedQuestions = appealDto.getQuestion()
                .stream().map(questionService::save).toList();
//--------------------
        var savedRegions = appealDto.getRegion();
        regionService.save(savedRegions);
        appealDto.setRegion(savedRegions);
        //        -----------------


        appealDto.setAuthors(savedAuthors);
        appealDto.setQuestion(savedQuestions);
        final String NUMBER = nomenclatureService
                .getNumberFromTemplate(appealDto.getNomenclature());
        appealDto.setNumber(NUMBER);

        var response = TEMPLATE.postForObject(getURIByInstance
                (instanceInfo, EMPTY), appealDto, AppealDto.class);
        return response;
    }

    @Override
    public void moveToArchive(Long id) {
        InstanceInfo instanceInfo = getInstance();
        var uri = getURIByInstance(instanceInfo, String.format("/toArchive/%s", id.toString()));
        TEMPLATE.put(uri, null);
    }

    @Override
    public AppealDto findById(Long id) {
        InstanceInfo instanceInfo = getInstance();
        var uri = getURIByInstance(instanceInfo, String.format("/byId/%s", id.toString()));
        AppealDto response = TEMPLATE.getForObject(uri, AppealDto.class);

        if (response != null && response.getAppealStatus().equals(EnumAppealStatus.NEW)) {
            amqpTemplate.convertAndSend(RabbitConstant.exchange, RabbitConstant.addressAppealIsRead,
                    new AppealReadRecord(id, new Date()));
        }
        return response;
    }

    @Override
    public List<AppealDto> findAllById(Iterable<Long> ids) {
        InstanceInfo instanceInfo = getInstance();
        String path = ids.toString().substring(1, ids.toString().length() - 1);
        var uri = getURIByInstance(instanceInfo, String.format("/allById/%s", path));
        AppealDto[] response = TEMPLATE.getForObject(uri, AppealDto[].class);
        return Arrays.asList(response);
    }

    @Override
    public AppealDto findByIdNotArchived(Long id) {
        InstanceInfo instanceInfo = getInstance();
        var uri = getURIByInstance(instanceInfo, String.format("/notArchived/%s", id.toString()));
        AppealDto response = TEMPLATE.getForObject(uri, AppealDto.class);
        return response;
    }

    @Override
    public List<AppealDto> findAllByIdNotArchived(Iterable<Long> ids) {
        InstanceInfo instanceInfo = getInstance();
        String path = ids.toString().substring(1, ids.toString().length() - 1);
        var uri = getURIByInstance(instanceInfo, String.format("/allNotArchived/%s", path));
        AppealDto[] response = TEMPLATE.getForObject(uri, AppealDto[].class);
        return Arrays.asList(response);
    }

    @Override
    public List<AppealAbbreviatedDto> findAllByIdEmployee(Long first, Long amount) {
        InstanceInfo instanceInfo = getInstance();
        String path = "/appealsByEmployee/?first=" +
                first +
                "&amount=" +
                amount;
        var uri = getURIByInstance(instanceInfo, path);
        AppealAbbreviatedDto[] response = TEMPLATE.getForObject(uri, AppealAbbreviatedDto[].class);
        return Arrays.asList(response);
    }

    private String validateAppealDto(AppealDto appealDto) {

        final String emailRegEx = "\\w+@\\w+\\.\\w+";
        final String phoneRegEx = "7\\d{10}";

        StringBuilder stringBuilder = new StringBuilder();
        Consumer<String> addIssue = (s) -> stringBuilder.append(s).append(";");

        var questions = appealDto.getQuestion();
        if (isEmpty(questions)) {
            addIssue.accept("field question is empty");
        } else {

            for (var question : questions) {

                if (!hasLength(question.getSummary())) {

                    addIssue.accept(String.format("question id: %d  field summary is empty or NULL",
                            question.getId()));
                } else if (question.getSummary().length() > 200) {

                    addIssue.accept(String.format("question id: %d field Summary has more then 200 characters",
                            question.getId()));
                }

                if (question.getTheme() == null) {
                    addIssue.accept(String.format("question id: %d theme is empty", question.getId()));
                }
            }
        }

        var authors = appealDto.getAuthors();
        if (isEmpty(authors)) {
            addIssue.accept("this appeal has no authors");
        } else {

            for (var author : authors) {
                var authorEmail = author.getEmail();
                if (authorEmail == null) {
                    addIssue.accept(String.format("author with id: %d field email is NULL", author.getId()));
                } else if (!authorEmail.matches(emailRegEx)) {

                    addIssue.accept(String.format("author with id: %d has email, but incorrect format", author.getId()));
                }

                var authorMobilePhone = author.getMobilePhone();
                if (!hasLength(authorMobilePhone)) {
                    addIssue.accept(String.format("author with id: %d has email is NULL", author.getId()));
                } else if (!authorMobilePhone.matches(phoneRegEx)) {

                    addIssue.accept(String.format("author with id: %d has mobilePhone, but incorrect format",
                            author.getId()));
                }

                var authorFirstName = author.getFirstName();
                if (!hasLength(authorFirstName)) {
                    addIssue.accept(String.format("author with id: %d has first name either empty or NULL",
                            author.getId()));
                } else if (authorFirstName.length() > 60) {
                    addIssue.accept(String.format("author with id: %d has first name length more 60 characters",
                            author.getId()));
                }

                var authorLastName = author.getLastName();
                if (!hasLength(authorLastName)) {
                    addIssue.accept(String.format("author with id: %d has author last name either empty or NULL",
                            author.getId()));
                } else if (authorLastName.length() > 60) {
                    addIssue.accept(String.format("author with id: %d has last name length more 60 characters",
                            author.getId()));
                }
            }
        }

        if (appealDto.getSendingMethod() == null) {
            addIssue.accept("appeal has no sending method");
        }

        if (!stringBuilder.isEmpty()) {
            stringBuilder.insert(0, "Appeal not valid;");
        }

        return stringBuilder.toString();
    }
}
























