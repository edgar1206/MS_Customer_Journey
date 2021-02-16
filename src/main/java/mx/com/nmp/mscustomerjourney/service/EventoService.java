package mx.com.nmp.mscustomerjourney.service;

import mx.com.nmp.mscustomerjourney.model.NR.Evento;
import mx.com.nmp.mscustomerjourney.model.log.LogsDTO;
import mx.com.nmp.mscustomerjourney.model.constant.Constants;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;
import java.util.logging.Logger;

@Service
public class EventoService {

    private static final Logger LOGGER = Logger.getLogger(EventoService.class.getName());

    @Autowired
    private Constants constants;

    @Autowired
    private IncidenciaService incidenciaService;

    @RabbitListener(queues = "Logs-MiMonte")
    public void recibeMensaje(Message message){
        System.out.println(new String(message.getBody()));
    }

    public void recibeLog(LogsDTO log){
        Evento evento = estandarizacionLog(log);
        guardaLog(evento);
        procesaLog(evento);
    }

    @Async
    private void guardaLog(Evento evento){
        try{
            RestTemplate restTemplate = new RestTemplate();
            Thread.sleep(1);
            HttpEntity<Evento> request = new HttpEntity<>(evento);
            restTemplate.postForEntity(constants.getMS_EVENTOS_URL(),request,String.class);
        }catch (HttpClientErrorException | InterruptedException e){
            LOGGER.info("Error " + e.getMessage());
        }
    }

    @Async
    public void procesaLog(Evento evento){
        incidenciaService.categorizar(evento);
    }

    public Evento estandarizacionLog(LogsDTO logsDTO) {
        Evento evento = new Evento();
        evento.setIdEvent(UUID.randomUUID().toString());
        evento.setEventType(constants.getEVENT_TYPE());
        evento.setEventLevel(logsDTO.getLevel());
        evento.setEventCategory(logsDTO.getCategoryName());
        evento.setEventAction(logsDTO.getAccion());
        evento.setEventDescription(logsDTO.getDescripcion());
        evento.setEventResource(logsDTO.getRecurso());
        evento.setTimeGenerated(logsDTO.getStartTime());
        evento.setEventPhase(logsDTO.getFase());
        evento.setApplicationName(constants.getAPPLICATION_NAME());
        evento.setConfigurationElement("Elemento configuracion");
        evento.setResolutionTower(constants.getRESOLUTION_TOWER());
        return evento;
    }

}
