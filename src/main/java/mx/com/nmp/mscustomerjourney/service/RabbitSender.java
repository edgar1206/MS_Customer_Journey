package mx.com.nmp.mscustomerjourney.service;

import mx.com.nmp.mscustomerjourney.model.NR.Evento;
import mx.com.nmp.mscustomerjourney.model.constant.Constants;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class RabbitSender {

    private static final Logger LOGGER = Logger.getLogger(RabbitSender.class.getName());

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private Constants constants;

    public void enviaEvento(Evento evento) {
        try{
            rabbitTemplate.convertAndSend(constants.getEXCHANGE(), constants.getROUTING_KEY_EXPERIENCIA(),evento);
        }
        catch (Exception e){
            LOGGER.info(e.getMessage());
        }
    }

}
