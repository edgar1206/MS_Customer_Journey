package mx.com.nmp.mscustomerjourney.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import mx.com.nmp.mscustomerjourney.model.NR.Evento;
import mx.com.nmp.mscustomerjourney.model.log.LogsDTO;
import mx.com.nmp.mscustomerjourney.modelError.TipoError1;
import mx.com.nmp.mscustomerjourney.modelError.TipoError2;
import mx.com.nmp.mscustomerjourney.modelError.TipoError3;
import mx.com.nmp.mscustomerjourney.modelError.TipoError4;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Categoriza {

    public void categorizar(String stringEvento){
        Gson gson = new Gson();
       // LogsDTO evento = gson.fromJson(stringEvento, LogsDTO.class);
        try {
            LogsDTO evento = new ObjectMapper().readValue(stringEvento, LogsDTO.class);
            if(evento.getLevel().equalsIgnoreCase("Error")){
                verificaNivelError(evento);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    private void verificaNivelError (LogsDTO evento){
        System.out.println();
        Gson gson = new Gson();
        TipoError1 tipoError1 = gson.fromJson(evento.getDescripcion(), TipoError1.class);

        System.out.println("tipo error " +tipoError1.getCodigoError());
        if(tipoError1.getCodigoError() != null && tipoError1.getCodigoError().equalsIgnoreCase("NMP-30001")){
            System.out.println(gson.toJson(tipoError1));
            generaIncidencia(evento);
        }
        TipoError2 tipoError2 = gson.fromJson(evento.getDescripcion(), TipoError2.class);
        if(tipoError2.getCode() != null){
            System.out.println(gson.toJson(tipoError2));
        }
        TipoError3 tipoError3 = gson.fromJson(evento.getDescripcion(), TipoError3.class);
        if(tipoError3.getError_code() != null){
            System.out.println(gson.toJson(tipoError3));
        }
        TipoError4 tipoError4 = gson.fromJson(evento.getDescripcion(), TipoError4.class);
        if(tipoError4.getError() != null){
            System.out.println(gson.toJson(tipoError4));
        }
    }

    private void generaIncidencia(LogsDTO evento) {
        Evento notificacion = new Evento();
        notificacion.setIdEvent(UUID.randomUUID().toString());
        notificacion.setEventType("Mimonte");
        notificacion.setEventLevel(evento.getLevel());
        notificacion.setEventCategory(evento.getCategoryName());
        notificacion.setEventAction(evento.getAccion());
        notificacion.setEventDescription(evento.getDescripcion());
        notificacion.setEventResource(evento.getRecurso());
        notificacion.setTimeGenerated(evento.getStartTime());
        notificacion.setSeverity("500");
    }


}
