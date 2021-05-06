package mx.com.nmp.mscustomerjourney.config;

import mx.com.nmp.mscustomerjourney.model.constant.Constants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

@Configuration
public class RabbitConfig {

    @Autowired
    Constants constants;

    @Bean
    public ConnectionFactory connectionFactory() {
        com.rabbitmq.client.ConnectionFactory rabbitmqConnectionfactory = new com.rabbitmq.client.ConnectionFactory();
        SSLSocketFactory sslSocketFactory;
        if(constants.getSSL_ENABLED().equalsIgnoreCase("true")){
            SSLContext sslContext;
            try {
                sslContext = SSLContext.getInstance(constants.getRABBIT_SSL_ALGORITHM());
                sslContext.init(null,null,null);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
            sslSocketFactory = (SSLSocketFactory)sslContext.getSocketFactory();
            rabbitmqConnectionfactory.setSocketFactory(sslSocketFactory);
        }
        rabbitmqConnectionfactory.setHost(constants.getRABBIT_HOST());
        rabbitmqConnectionfactory.setPort(Integer.parseInt(constants.getRABBIT_PORT()));
        rabbitmqConnectionfactory.setVirtualHost(constants.getRABBIT_VIRTUAL_HOST());
        rabbitmqConnectionfactory.setUsername(constants.getRABBIT_USERNAME());
        rabbitmqConnectionfactory.setPassword(constants.getRABBIT_SCT());
        rabbitmqConnectionfactory.setAutomaticRecoveryEnabled(false);
        return new CachingConnectionFactory(rabbitmqConnectionfactory);
    }

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