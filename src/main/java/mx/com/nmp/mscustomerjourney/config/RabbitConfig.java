package mx.com.nmp.mscustomerjourney.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitConfig {

    //private static final String EXCHANGE_NAME = "Direc-Depuracion";

    String Depuracion = "Depuracion";


    String QUEUE2 = "queue2";

    String QUEUE3 = "queue3";


    String FANOUT_EXCHANGE = "Exchange-MiMonte";


    //@Bean
    //HeadersExchange exchange() {
    //  return new HeadersExchange(EXCHANGE_NAME);
    //}

    @Bean
    public Queue queue1() {
        return new Queue("Logs-MiMonte");
    }

    @Bean
    public Queue queue2() {
        return new Queue("Notificaciones");
    }

    @Bean
    public Queue queue3() {
        return new Queue("Experiencias");
    }

    @Bean
    public DirectExchange fanoutExchange() {
        return new DirectExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(queue2()).to(fanoutExchange()).with("key-MiMonte");
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(queue3()).to(fanoutExchange()).with("key-notificaciones");
    }

    @Bean
    public Binding binding3() {
        return BindingBuilder.bind(queue3()).to(fanoutExchange()).with("key-experiencias");
    }


    @Bean
    public RabbitTemplate rabbitTemplate(org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

}