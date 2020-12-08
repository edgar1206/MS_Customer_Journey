package mx.com.nmp.mscustomerjourney.service;

import com.google.gson.Gson;
import mx.com.nmp.mscustomerjourney.model.NR.LogIncidencia;
import mx.com.nmp.mscustomerjourney.model.log.LogsDTO;
import mx.com.nmp.mscustomerjourney.model.constant.Constants;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class RabbitConsumer {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @RabbitListener(queues = "${spring.rabbitmq.queue.eventos}")
    public void recibeLog(Message message){

        procesaLog(new String(message.getBody()));

        guardaLog(new String(message.getBody()));

    }

    private void guardaLog(String StringLog){
        Gson gson = new Gson();
        LogsDTO log = gson.fromJson(StringLog, LogsDTO.class);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<LogsDTO> request = new HttpEntity<>(log);
        try{
            restTemplate.postForEntity(Constants.MS_EVENTOS_URL,request,LogsDTO.class);
        }catch (HttpClientErrorException e){
            e.printStackTrace();
        }
    }

    private void procesaLog(String StringLog){
        Gson gson = new Gson();
        LogsDTO log = gson.fromJson(StringLog, LogsDTO.class);
        if(log.getLevel().equalsIgnoreCase("ERROR")){
            RestTemplate restTemplate = new RestTemplate();

            LogIncidencia notificacion = new LogIncidencia();
            notificacion.setIdEvento((String.valueOf(new Date().getTime() / 10000)));
            notificacion.setError(log.getRecurso());
            notificacion.setErrorDescripcion(log.getFase());
            notificacion.setHoraOcurrencia(log.getStartTime());
            //notificacion.setSeveridad();
            notificacion.setStackTrace(log.getDescripcion());
            notificacion.setStatusCodeError("500");
            notificacion.setTorreResolucion("Jona");

            try{
                restTemplate.postForEntity(Constants.MS_EVENTOS_INCIDENCIA,notificacion,String.class);
            }catch (HttpClientErrorException e){
                e.printStackTrace();
            }

        }
    }

}
