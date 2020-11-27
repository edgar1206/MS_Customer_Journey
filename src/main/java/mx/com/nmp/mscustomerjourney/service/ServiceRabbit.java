package mx.com.nmp.mscustomerjourney.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ServiceRabbit {

    @RabbitListener(queues = "${sample.rabbitmq.queue}")
    public void recibeMensaje(Message message) {


    }

}
