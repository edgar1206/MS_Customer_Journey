package mx.com.nmp.mscustomerjourney.service;


import com.google.gson.Gson;
import mx.com.nmp.mscustomerjourney.model.NR.Evento;
import mx.com.nmp.mscustomerjourney.model.catalogo.Errores;
import mx.com.nmp.mscustomerjourney.modelError.TipoError1;
import mx.com.nmp.mscustomerjourney.modelError.TipoError2;
import mx.com.nmp.mscustomerjourney.modelError.TipoError3;
import mx.com.nmp.mscustomerjourney.modelError.TipoError4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class Categoriza {

    private Integer tiempo=15;

    @Autowired
    private MongoService mongoService;

    public void categorizar(Evento evento){
            if(evento.getEventLevel().equalsIgnoreCase("Error")){
                getCodigoError(evento);
            }

    }

    private void getCodigoError (Evento evento){
        System.out.println();
        Gson gson = new Gson();
        TipoError1 tipoError1 = gson.fromJson(evento.getEventDescription(), TipoError1.class);
        if(tipoError1.getCodigoError() != null){
            verificaError(tipoError1.getCodigoError(),evento);
        }
        TipoError2 tipoError2 = gson.fromJson(evento.getEventDescription(), TipoError2.class);
        if(tipoError2.getCode() != null){
            verificaError(tipoError2.getCode(),evento);
        }
        TipoError3 tipoError3 = gson.fromJson(evento.getEventDescription(), TipoError3.class);
        if(tipoError3.getError_code() != null){
            verificaError(tipoError3.getError_code(),evento);
        }
        TipoError4 tipoError4 = gson.fromJson(evento.getEventDescription(), TipoError4.class);
        if(tipoError4.getError() != null){
            verificaError(tipoError4.getError(),evento);
        }
    }

    private void verificaError(String codigoError, Evento evento){
        System.out.println("Codigo error "+codigoError);
        List<Errores> listaErrores= mongoService.getListaErrores();
        for (Errores error:listaErrores){
            long tiempoMs = new Date().getTime() - error.getUltimaActualizacion().getTime();
            long diferenciaMinutos = tiempoMs / (1000);
            if(error.getCodigoError().equalsIgnoreCase(codigoError)){
                if(error.getAlertamiento()==null || error.getAlertamiento().isEmpty()){
                    error.setAlertamiento("LOW");
                    error.setUltimaActualizacion(new Date());
                    mongoService.saveError(error);
                    generaIncidencia(error.getAlertamiento(),evento);
                }else if( diferenciaMinutos >= tiempo && error.getAlertamiento().equalsIgnoreCase("LOW")){
                   error.setAlertamiento("MEDIUM");
                   error.setUltimaActualizacion(new Date());
                   mongoService.saveError(error);
                   generaIncidencia(error.getAlertamiento(),evento);
                }else if( diferenciaMinutos >= tiempo && error.getAlertamiento().equalsIgnoreCase("MEDIUM")){
                    error.setAlertamiento("HIGH");
                    error.setUltimaActualizacion(new Date());
                    mongoService.saveError(error);
                    generaIncidencia(error.getAlertamiento(),evento);
                }

            }
        }
    }


    private void generaIncidencia(String alertamiento, Evento evento) {
        System.out.println("Incidencia generada");
      evento.setSeverity(alertamiento);
    }


}
