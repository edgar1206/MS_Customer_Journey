package mx.com.nmp.mscustomerjourney.service;


import com.google.gson.Gson;
import mx.com.nmp.mscustomerjourney.model.NR.Evento;
import mx.com.nmp.mscustomerjourney.model.catalogo.Errores;
import mx.com.nmp.mscustomerjourney.model.constant.Constants;
import mx.com.nmp.mscustomerjourney.modelError.MidasError;
import mx.com.nmp.mscustomerjourney.modelError.CognitoError;
import mx.com.nmp.mscustomerjourney.modelError.OpenpayError;
import mx.com.nmp.mscustomerjourney.modelError.TipoError4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class Categoriza {

    @Autowired
    private Constants constants;

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
        MidasError midasError = gson.fromJson(evento.getEventDescription(), MidasError.class);
        if(midasError.getCodigoError() != null){
            verificaError(midasError.getCodigoError(),evento);
        }
        CognitoError cognitoError = gson.fromJson(evento.getEventDescription(), CognitoError.class);
        if(cognitoError.getCode() != null){
            verificaError(cognitoError.getCode(),evento);
        }
        OpenpayError openpayError = gson.fromJson(evento.getEventDescription(), OpenpayError.class);
        if(openpayError.getError_code() != null){
            verificaError(openpayError.getError_code(),evento);
        }
        TipoError4 tipoError4 = gson.fromJson(evento.getEventDescription(), TipoError4.class);
        if(tipoError4.getError() != null){
            verificaError(tipoError4.getError(),evento);
        }
    }

    private void verificaError(String codigoError, Evento evento){
        System.out.println("Codigo error "+codigoError);
        List<Errores> listaErrores= mongoService.getListaErrores();
        if(listaErrores.isEmpty() || listaErrores==null){
            System.out.println("Catalogo vacio");
            return;
        }
        for (Errores error:listaErrores){
            long tiempoMs = new Date().getTime() - error.getUltimaActualizacion().getTime();
            long diferenciaMinutos = tiempoMs / (1000 * 60);
            if(error.getCodigoError().equalsIgnoreCase(codigoError)){
                if(error.getAlertamiento()==null || error.getAlertamiento().isEmpty()){
                    error.setAlertamiento("LOW");
                    error.setUltimaActualizacion(new Date());
                    mongoService.saveError(error);
                    generaIncidencia(error.getAlertamiento(),evento);
                }else if( diferenciaMinutos >= Integer.valueOf(constants.getTIEMPO_AUMENTA_ALERTAMIENTO()) && error.getAlertamiento().equalsIgnoreCase("LOW")){
                   error.setAlertamiento("MEDIUM");
                   error.setUltimaActualizacion(new Date());
                   mongoService.saveError(error);
                   generaIncidencia(error.getAlertamiento(),evento);
                }else if( diferenciaMinutos >= Integer.valueOf(constants.getTIEMPO_AUMENTA_ALERTAMIENTO())&& error.getAlertamiento().equalsIgnoreCase("MEDIUM")){
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
