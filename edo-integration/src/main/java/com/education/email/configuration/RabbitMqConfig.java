package com.education.email.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.education.model.constant.RabbitConstant;


@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue newEmailQueue() {
        return new Queue(RabbitConstant.addressCreateEmailQueue, false);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(RabbitConstant.exchange);
    }

    @Bean
    public Binding binding (Queue newEmailQueue, DirectExchange exchange) {
        return BindingBuilder
                .bind(newEmailQueue)
                .to(exchange)
                .with(RabbitConstant.addressCreateEmailQueue);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
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
