package com.education.configuration;

import com.education.model.dto.*;
import com.education.model.enumEntity.EnumAppealStatus;
import com.education.model.enumEntity.EnumWayToReceive;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class ObjectsSupplier {


    public AppealDto getAppeal() {

        return getCorrectAppeal();
    }

    public AppealDto getBadNoQuestionNoSendingMethod() {
        var appeal = getCorrectAppeal();

        appeal.setQuestion(new LinkedList<>());
        appeal.setSendingMethod(null);

        return appeal;
    }

    public AppealDto getBad_FirstAuthorNoName_SecondAuthorNoLastnameLargeFirstname_ThirdBadEmailNoPhone() {
        AppealDto appeal = getCorrectAppeal();
        List<AuthorDto> authors = appeal.getAuthors();
        var firstAuthor = authors.get(0);
        var secondAuthor = authors.get(1);
        var thirdAuthor = authors.get(2);
        secondAuthor.setId(2l);
        secondAuthor.setLastName("");
        secondAuthor.setFirstName("asdfasdfasasdfasdfasasdfasdfasasdfasdfasasdfasdfasasdfasdfasasdfasdfasasdfasdfasasdfasdfas");
        firstAuthor.setId(1l);
        firstAuthor.setFirstName("");
        thirdAuthor.setId(3l);
        thirdAuthor.setEmail("lala@lala");
        thirdAuthor.setMobilePhone("911");
        return appeal;
    }


    public AppealDto getBad_FirstQuestionNoSummary_SecondQuestionNoTheme_ThirdQuestionLargeSummary_FirstAuthorLargeLastnameNoEmail() {

        var appeal = getCorrectAppeal();

        List<QuestionDto> questions = appeal.getQuestion();
        var questionsFirst = questions.get(0);
        var questionSecond = questions.get(1);
        var questionThird = questions.get(2);
        var authorFirst = appeal.getAuthors().get(0);

        questionsFirst.setId(1l);
        questionsFirst.setSummary(null);

        questionSecond.setId(2l);
        questionSecond.setTheme(null);

        questionThird.setId(3l);
        questionThird.setSummary("laskfjl;askfasdfasfl;sdflasdflasdflasdflkasdflkajsfjsdfjkdaslfjasklfasklfasdklflsdkflaskdfjaklsflasdflasjflkasfjklasklfjaslfjasdl;fjasdl;fjsdal;fasdlkfasdl;flasdkfasdklfasdkl;jlk;jklf;asdjfkl;dasjfl;kadsjfkl;asdkl;l;sklflaskfakllasdkfjlkaklafjlkasdjfkl;aklajlakskl;ajfakl;fjaklasdl;jasdklj");

        authorFirst.setId(1l);
        authorFirst.setLastName("asdlkfjsdflkasdlfasdlfasdlfasdfsdlafasdlfasdklfkl;asfkladlsdkaflsdkafsdkjfl;sdkjfl;asdkjfl;sdkjfsdkl;afjakl;sdfjkl;ajkl;ajklakl;asdjfklajklaflasdkjflkasdfklasfkl;asdfkl;asdj;");
        authorFirst.setEmail(null);

        return appeal;
    }

    public AppealDto getBadAppNullSummary() {

        AppealDto appealDto = getCorrectAppeal();
        appealDto.getQuestion().get(0).setSummary(null);

        return appealDto;
    }

    public AppealDto getBadAppEmptySummary() {

        AppealDto appealDto = getCorrectAppeal();
        appealDto.getQuestion().get(0).setSummary("");

        return appealDto;
    }

    public AppealDto getBadAppSummaryMore200ThemeNull() {

        AppealDto appealDto = getCorrectAppeal();

        QuestionDto question1 = appealDto.getQuestion().get(0);
        question1.setTheme(null);
        question1.setSummary("fistingfistingfistingfistingfistingfistingfisfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfistingfisting");

        return appealDto;
    }


    public AppealDto getBadAppQuestion() {  //todo hz strange idk

        AppealDto appealDto = new AppealDto();
        QuestionDto question1 = new QuestionDto();
        question1.setSummary("fisting");
        ThemeDto themeDto = new ThemeDto();
        themeDto.setName("price");
        question1.setTheme(themeDto);

        return appealDto;
    }

    public AppealDto getBadAppNoSendingNoQuestion() {
        AppealDto appeal = getCorrectAppeal();
        appeal.setQuestion(null);
        appeal.setSendingMethod(null);
        return appeal;
    }

    public AppealDto getBadEmailLastMore60FirstnameAbsent() {
        AppealDto appeal = getCorrectAppeal();

        AuthorDto author = appeal.getAuthors().get(0);
        author.setId(1l);
        author.setEmail("asfdasf@tutby");
        author.setLastName("asdfasdfasdfasfkjsdfkasfjaslflawdklawjlfjawlfkaslkjflkasjdflkjaslkjaljkflkasdjlfjkasljkflasdkjflasdjkflksdlfjkasdlsaflkjsadflaslfsadflkaslfasdlfjksadlfjasljkf");
        author.setFirstName(null);

        return appeal;
    }

    public AppealDto getBadEmpty() {
        AppealDto appeal = new AppealDto();

        return appeal;
    }


    private AppealDto getCorrectAppeal() {
        Function<AppealDto, AppealDto> copyAppeal = (appeal) -> {
            AppealDto appealCopy = new AppealDto();
            appealCopy.setAppealStatus(appeal.getAppealStatus());
            appealCopy.setAuthors(appeal.getAuthors());
            appealCopy.setNomenclature(appeal.getNomenclature());
            appealCopy.setFile(appeal.getFile());
            appealCopy.setQuestion(appeal.getQuestion());
            appealCopy.setSendingMethod(appeal.getSendingMethod());
            appealCopy.setSigner(appeal.getSigner());
            appealCopy.setAddressee(appealCopy.getAddressee());
            appealCopy.setAnnotation(appeal.getAnnotation());
            appealCopy.setNumber(appeal.getNumber());
            appealCopy.setCreator(appeal.getCreator());
            appealCopy.setCreationDate(appeal.getCreationDate());
            appealCopy.setArchivedDate(appeal.getArchivedDate());
            appealCopy.setId(appeal.getId());
            return appealCopy;
        };

        Function<AuthorDto, AuthorDto> copyAuthor = (author) -> {
            AuthorDto authorCopy = new AuthorDto();
            authorCopy.setId(author.getId());
            authorCopy.setMobilePhone(author.getMobilePhone());
            authorCopy.setEmail(author.getEmail());
            authorCopy.setLastName(author.getLastName());
            authorCopy.setFirstName(author.getFirstName());
            authorCopy.setAddress(author.getAddress());
            authorCopy.setEmployment(author.getEmployment());
            authorCopy.setFioDative(author.getFioDative());
            authorCopy.setFioGenitive(author.getFioGenitive());
            authorCopy.setFioNominative(author.getFioNominative());
            authorCopy.setSnils(author.getSnils());
            authorCopy.setMiddleName(author.getMiddleName());

            return authorCopy;
        };

        Function<QuestionDto, QuestionDto> copyQuestion = (question) -> {
            QuestionDto questionCopy = new QuestionDto();

            questionCopy.setId((long) getRandom100());
            questionCopy.setTheme(question.getTheme());
            questionCopy.setSummary(question.getSummary());
            questionCopy.setArchivedDate(question.getArchivedDate());
            questionCopy.setTheme(question.getTheme());
            questionCopy.setCreationDate(question.getCreationDate());

            return questionCopy;
        };

        var correctAppeal = new AppealDto();
        correctAppeal.setId(3l);
        correctAppeal.setCreationDate(ZonedDateTime.now());
        correctAppeal.setNumber("correctAppeal");
        correctAppeal.setAnnotation("correctAppeal");
        List<EmployeeDto> employeesSigners = new LinkedList<>();
        EmployeeDto employeFirst = new EmployeeDto();
        employeesSigners.add(employeFirst);
        correctAppeal.setSigner(employeesSigners);
        correctAppeal.setCreator(employeFirst);

        List<EmployeeDto> employeeAddressee = new LinkedList<>();
        EmployeeDto employeeAddresseeFirst = new EmployeeDto();
        employeesSigners.add(employeeAddresseeFirst);
        correctAppeal.setAddressee(employeeAddressee);

        NomenclatureDto nomenclatureDto = new NomenclatureDto();
        correctAppeal.setNomenclature(nomenclatureDto);

        AuthorDto author1 = new AuthorDto();
        author1.setId(4l);
        author1.setEmail("test@test.com");
        author1.setMobilePhone("71111111111");
        author1.setFirstName("testAuthor");
        author1.setLastName("testLastName");

        var author2 = copyAuthor.apply(author1);
        var author3 = copyAuthor.apply(author1);


        LinkedList<AuthorDto> authors = new LinkedList<>();
        authors.add(author1);
        authors.add(author2);
        authors.add(author3);
        correctAppeal.setAuthors(authors);

        correctAppeal.setFile(new LinkedList<>());

        QuestionDto question1 = new QuestionDto();
        ThemeDto themeDto = new ThemeDto();
        themeDto.setName("forCorrectAppeal");
        themeDto.setId(1l);

        question1.setId(1l);
        question1.setTheme(themeDto);
        question1.setSummary("for Correct Appeal");

        var question2 = copyQuestion.apply(question1);
        var question3 = copyQuestion.apply(question1);


        LinkedList<QuestionDto> questions = new LinkedList<>(Arrays.asList(question1, question2, question3));
        correctAppeal.setQuestion(questions);

        correctAppeal.setAppealStatus(EnumAppealStatus.NEW);
        correctAppeal.setSendingMethod(EnumWayToReceive.ON_PAPER);

        return correctAppeal;
    }

    private int getRandom100() {
        return (int) (100 * Math.random());
    }

}




















