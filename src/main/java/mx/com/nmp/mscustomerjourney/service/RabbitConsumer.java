package mx.com.nmp.mscustomerjourney.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sun.corba.se.spi.ior.ObjectId;
import mx.com.nmp.mscustomerjourney.model.NR.LogIncidencia;
import mx.com.nmp.mscustomerjourney.model.log.LogsDTO;
import mx.com.nmp.mscustomerjourney.model.constant.Constants;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class RabbitConsumer {

    private static final Logger LOGGER = Logger.getLogger(RabbitConsumer.class.getName());

    @Autowired
    private Categoriza categoriza;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @RabbitListener(queues = "${spring.rabbitmq.queue.eventos}")
    public void recibeLog(Message message){
        guardaLog(new String(message.getBody()));
        procesaLog(new String(message.getBody()));
    }

    @Async
    private void guardaLog(String stringEvento){
        Gson gson = new Gson();
        System.out.println();
        System.out.println(stringEvento);
        //LogsDTO evento = gson.fromJson(stringEvento, LogsDTO.class);
        //evento.setAccion("ninguna");
        //System.out.println(gson.toJson(evento));
        try{
            LogsDTO evento = new ObjectMapper().readValue(stringEvento, LogsDTO.class);
            RestTemplate restTemplate = new RestTemplate();
            Thread.sleep(1);
            System.out.println(gson.toJson(evento));
            System.out.println("termina");
            HttpEntity<LogsDTO> request = new HttpEntity<>(evento);
            restTemplate.postForEntity(Constants.MS_EVENTOS_URL,request,String.class);
        }catch (HttpClientErrorException | InterruptedException e){
            LOGGER.info(e.getMessage());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Async
    private void procesaLog(String stringEvento){
        categoriza.categorizar(stringEvento);
    }

}
