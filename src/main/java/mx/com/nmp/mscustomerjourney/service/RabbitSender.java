package mx.com.nmp.mscustomerjourney.service;

import com.google.gson.Gson;
import mx.com.nmp.mscustomerjourney.model.log.LogsDTO;
import mx.com.nmp.mscustomerjourney.model.constant.Constants;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class RabbitSender {

    private static final Logger LOGGER = Logger.getLogger(RabbitSender.class.getName());

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void enviaLog(String log) {
        Message message = MessageBuilder.withBody(log.getBytes()).build();
        rabbitTemplate.convertAndSend(Constants.EXCHANGE,Constants.ROUTING_KEY_EVENTOS,message);
        LOGGER.info("envia mensaje a rabbit");
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
