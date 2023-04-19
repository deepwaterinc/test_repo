package com.education.configuration;

import com.education.model.constant.RabbitConstant;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue addressCreateDB(){
        return new Queue(RabbitConstant.addressCreateDBQueue, false);
    }

    @Bean
    public Queue addressCreateService(){
        return new Queue(RabbitConstant.addressCreateServiceQueue, false);
    }

    @Bean
    public Queue appealReadService(){
        return new Queue(RabbitConstant.addressAppealIsRead, false);
    }
    @Bean
    public DirectExchange exchange(){
         return new DirectExchange(RabbitConstant.exchange);
    }

    @Bean
    public Binding binding(Queue addressCreateDB, DirectExchange exchange){
        return BindingBuilder
                .bind(addressCreateDB)
                .to(exchange)
                .with(addressCreateDB.getName());
    }


    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory jsaFactory(ConnectionFactory connectionFactory,
                                                           SimpleRabbitListenerContainerFactoryConfigurer configurer) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setMessageConverter(jsonMessageConverter());
        return factory;
    }
}
