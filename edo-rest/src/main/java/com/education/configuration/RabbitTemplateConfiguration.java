package com.education.configuration;

import com.education.model.constant.RabbitConstant;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация RabbitMQ. Пример создания двух очередей и связывание их с routing key
 */
@Configuration
public class RabbitTemplateConfiguration {

    @Bean
    public Queue addressCreate() {
        return new Queue(RabbitConstant.addressCreateQueue, false);
    }

    @Bean
    public DirectExchange exchange(){
        return new DirectExchange(RabbitConstant.exchange);
    }

    @Bean
    public Binding binding(Queue addressCreate, DirectExchange exchange){
        return BindingBuilder
                .bind(addressCreate)
                .to(exchange)
                .with(RabbitConstant.addressCreateQueue);
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

}
