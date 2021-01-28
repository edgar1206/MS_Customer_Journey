package mx.com.nmp.mscustomerjourney.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mx.com.nmp.mscustomerjourney.model.NR.Evento;
import mx.com.nmp.mscustomerjourney.model.log.LogsDTO;
import mx.com.nmp.mscustomerjourney.model.constant.Constants;
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
public class EventoService {

    private static final Logger LOGGER = Logger.getLogger(EventoService.class.getName());

    @Autowired
    private Constants constants;

    @Autowired
    private IncidenciaService incidenciaService;


    public void recibeLog(String log){
        try {
            LogsDTO logsDTO = new ObjectMapper().readValue(log, LogsDTO.class);
            Evento evento = estandarizacionLog(logsDTO);
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
    private void procesaLog(Evento evento){
        incidenciaService.categorizar(evento);
    }

    private Evento estandarizacionLog(LogsDTO logsDTO) {
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
