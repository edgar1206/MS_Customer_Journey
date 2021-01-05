package mx.com.nmp.mscustomerjourney.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import mx.com.nmp.mscustomerjourney.model.NR.Evento;
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

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class RabbitConsumer {

    private static final Logger LOGGER = Logger.getLogger(RabbitConsumer.class.getName());

    @Autowired
    private Categoriza categoriza;

    @Autowired
    private RabbitSender rabbitSender;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @RabbitListener(queues = "${spring.rabbitmq.queue.eventos}")
    public void recibeLog(Message message){
        try {
            LogsDTO logsDTO = new ObjectMapper().readValue(message.getBody(), LogsDTO.class);
            Evento evento= estandarizacionLog(logsDTO);
            rabbitSender.enviaEvento(evento);
            guardaLog(evento);
            procesaLog(evento);
        }catch (JsonParseException e){
            LOGGER.info(e.getMessage());
        }
        catch (IOException e){
            LOGGER.info(e.getMessage());
        }
    }

    @Async
    private void guardaLog(Evento evento){
        Gson gson = new Gson();
        System.out.println();
        try{
            RestTemplate restTemplate = new RestTemplate();
            Thread.sleep(1);
            System.out.println(gson.toJson(evento));
            System.out.println("termina");
            HttpEntity<Evento> request = new HttpEntity<>(evento);
            restTemplate.postForEntity(Constants.MS_EVENTOS_URL,request,String.class);
        }catch (HttpClientErrorException | InterruptedException e){
            LOGGER.info(e.getMessage());
        }
    }

    @Async
    private void procesaLog(Evento evento){
        categoriza.categorizar(evento);
    }

    private Evento estandarizacionLog(LogsDTO logsDTO) {

        Evento evento = new Evento();
        evento.setIdEvent(UUID.randomUUID().toString());
        evento.setEventType("Mimonte");
        evento.setEventLevel(logsDTO.getLevel());
        evento.setEventCategory(logsDTO.getCategoryName());
        evento.setEventAction(logsDTO.getAccion());
        evento.setEventDescription(logsDTO.getDescripcion());
        evento.setEventResource(logsDTO.getRecurso());
        evento.setTimeGenerated(logsDTO.getStartTime());
        return evento;
    }
}
