package com.education.service;

import com.education.entity.Address;
import com.education.model.dto.AddressDto;
import com.education.model.constant.RabbitConstant;
import com.education.repository.AddressRepository;
import com.education.util.Mapper.impl.AddressMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * Пример RabbitMQ Listener
 * Над методом необходимо указать аннотацию @RabbitListener с названием очереди
 */
@Service
@AllArgsConstructor
@Log4j2
public class RabbitRepoListenerExample {

    private AddressRepository addressRepository;

    private AddressMapper mapper;
    @RabbitListener(queues = RabbitConstant.addressCreateDBQueue)
    public void createAddress(AddressDto addressDto) {

        Address address = mapper.toEntity(addressDto);
        addressRepository.saveAndFlush(address);

        log.log(Level.INFO, "Сущность сохранена " + addressDto.toString());

    }
}
