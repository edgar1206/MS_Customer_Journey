package mx.com.nmp.mscustomerjourney.service;

import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import mx.com.nmp.mscustomerjourney.model.LogsDTO;
import mx.com.nmp.mscustomerjourney.model.constant.Constants;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.jar.JarOutputStream;


@Service
public class RabbitSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        Gson gson = new Gson();
        String json = gson.toJson("hola");
        System.out.println(json);
        Message message = MessageBuilder
                .withBody(json.getBytes())
                .build();
        System.out.println("enviando...");
        rabbitTemplate.convertAndSend(Constants.getEXCHANGE(), Constants.getROUTING(), message);
        System.out.println("mensajes enviados");
    }

    public void sendLog(LogsDTO log) {
        Gson gson = new Gson();
        String msj = gson.toJson(log);
        Message message = MessageBuilder.withBody(msj.getBytes()).build();
        rabbitTemplate.convertAndSend(Constants.getEXCHANGE(),Constants.getROUTING(),message);

       /* Connection connectionRabbit = RabbitMQConfig.getInstance(urlRabbit).newConnection();
        try{

            Channel channel =  connectionRabbit.createChannel();
            channel.queueDeclare(queuePagos, false, false, false, null);
            Map<String, Object> header = new HashMap<>();
            header.put("idDestino", headers.get("idDestino").get(0));
            header.put("idConsumidor", headers.get("idConsumidor").get(0));
            header.put("usuario", headers.get("usuario").get(0));
            header.put("Accept", headers.get("Accept").get(0));
            Gson gson = new Gson();
            String json = gson.toJson(pago);

            AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().headers(header).build();
            channel.basicPublish("", queuePagos, properties, json.getBytes());
            logger.info("Pago enviado a Rabbit Pago");

        }catch (Exception e){
            logger.info("Error: " + e.getMessage());
        }
        finally {
            connectionRabbit.close();
        }*/
    }

}
