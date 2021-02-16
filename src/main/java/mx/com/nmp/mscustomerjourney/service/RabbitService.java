package mx.com.nmp.mscustomerjourney.service;

import com.google.gson.Gson;
import mx.com.nmp.mscustomerjourney.model.NR.Evento;
import mx.com.nmp.mscustomerjourney.model.constant.Constants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class RabbitService {

    private static final Logger LOGGER = Logger.getLogger(RabbitService.class.getName());

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    Constants constants;

    public void enviaExperiencia(Evento experiencia){
        try{
            Gson gson = new Gson();
            rabbitTemplate.convertAndSend(constants.getHEADER(),constants.getROUTING_EXPERIENCIAS(), gson.toJson(experiencia));
        }catch (Exception e){
            LOGGER.info("Error al enviar a rabbit " + e.getMessage());
        }
    }

    public void enviaNotificacion(Evento notificacion){
        try{
            Gson gson = new Gson();
            rabbitTemplate.convertAndSend(constants.getHEADER(),constants.getROUTING_NOTIFICACIONES(), gson.toJson(notificacion));
        }catch (Exception e){
            LOGGER.info("Error al enviar a rabbit " + e.getMessage());
        }
    }

}
