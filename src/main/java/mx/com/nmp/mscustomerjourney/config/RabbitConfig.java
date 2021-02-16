package mx.com.nmp.mscustomerjourney.config;

import mx.com.nmp.mscustomerjourney.model.constant.Constants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Autowired
    Constants constants;

    @Bean
    public Queue logs() {
        return new Queue(constants.getQUEUE_LOGS());
    }

    @Bean
    public Queue notificaciones() {
        return new Queue(constants.getQUEUE_NOTIFICACIONES());
    }

    @Bean
    public Queue experiencias() {
        return new Queue(constants.getQUEUE_EXPERIENCIAS());
    }

    @Bean
    public DirectExchange fanoutExchange() {
        return new DirectExchange(constants.getHEADER());
    }

    @Bean
    public Binding bindingLogs() {
        return BindingBuilder.bind(logs()).to(fanoutExchange()).with(constants.getROUTING_LOGS());
    }

    @Bean
    public Binding bindingNot() {
        return BindingBuilder.bind(notificaciones()).to(fanoutExchange()).with(constants.getROUTING_NOTIFICACIONES());
    }

    @Bean
    public Binding bindingExp() {
        return BindingBuilder.bind(experiencias()).to(fanoutExchange()).with(constants.getROUTING_EXPERIENCIAS());
    }

    @Bean
    public RabbitTemplate rabbitTemplate(org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

}