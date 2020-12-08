package mx.com.nmp.mscustomerjourney.config;

import mx.com.nmp.mscustomerjourney.model.constant.Constants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    Queue queueEvento() {
        return new Queue(Constants.QUEUE_EVENTOS, false);
    }

    @Bean
    Queue queueNotificacion() {
        return new Queue(Constants.QUEUE_NOTIFICACIONES, false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(Constants.EXCHANGE);
    }

    @Bean
    Binding bindingEvento() {
        return BindingBuilder.bind(queueEvento()).to(exchange()).with(Constants.ROUTING_KEY_EVENTOS);
    }

    @Bean
    Binding bindingNotificacion() {
        return BindingBuilder.bind(queueNotificacion()).to(exchange()).with(Constants.ROUTING_KEY_NOTIFICACIONES);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }


}
