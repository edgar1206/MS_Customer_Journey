package mx.com.nmp.mscustomerjourney.service;

import com.google.gson.Gson;
import mx.com.nmp.mscustomerjourney.model.NR.Evento;
import mx.com.nmp.mscustomerjourney.model.catalogo.Errores;
import mx.com.nmp.mscustomerjourney.model.constant.Constants;
import mx.com.nmp.mscustomerjourney.modelError.MidasError;
import mx.com.nmp.mscustomerjourney.modelError.CognitoError;
import mx.com.nmp.mscustomerjourney.modelError.OpenpayError;
import mx.com.nmp.mscustomerjourney.modelError.OAuthError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service
public class IncidenciaService {

    private static final Logger LOGGER = Logger.getLogger(IncidenciaService.class.getName());

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
        OAuthError OAuthError = gson.fromJson(evento.getEventDescription(), OAuthError.class);
        if(OAuthError.getError() != null){
            verificaError(OAuthError.getError(),evento);
        }
    }

    private void verificaError(String codigoError, Evento evento){
        List<Errores> listaErrores = mongoService.getListaErrores();
        if(listaErrores == null){
            LOGGER.info("Catalogo de errores vacio.");
            return;
        }
        for (Errores error:listaErrores){
            long tiempoMs = new Date().getTime() - error.getUltimaActualizacion().getTime();
            long diferenciaMinutos = tiempoMs / (1000 * 60);
            if(error.getCodigoError().equalsIgnoreCase(codigoError)){
                if(error.getAlertamiento()==null || error.getAlertamiento().isEmpty()){
                    error.setAlertamiento("Low");
                    guardaAlertamiento(error, evento);
                }else if( diferenciaMinutos >= Integer.parseInt(constants.getTIEMPO_AUMENTA_ALERTAMIENTO()) && error.getAlertamiento().equalsIgnoreCase("Low")){
                    error.setAlertamiento("Moderate");
                    guardaAlertamiento(error, evento);
                }else if( diferenciaMinutos >= Integer.parseInt(constants.getTIEMPO_AUMENTA_ALERTAMIENTO())&& error.getAlertamiento().equalsIgnoreCase("Moderate")){
                    error.setAlertamiento("High");
                    guardaAlertamiento(error, evento);
                }else if( diferenciaMinutos >= Integer.parseInt(constants.getTIEMPO_AUMENTA_ALERTAMIENTO())&& error.getAlertamiento().equalsIgnoreCase("Medium")){
                    error.setAlertamiento("Critical");
                    guardaAlertamiento(error, evento);
                }
            }
        }
    }

    public void guardaAlertamiento(Errores error, Evento evento){
        if(evento.getEventType().equalsIgnoreCase(error.getNombreAplicacion())){
            error.setUltimaActualizacion(new Date());
            error.setRecurso(evento.getEventResource());
            evento.setSeverity(error.getAlertamiento());
            mongoService.saveError(error);
            generaIncidencia(evento);
        }
    }

    public void generaIncidencia(Evento evento) {
        try{
            LOGGER.info("Incidencia generada " + evento.getEventDescription() + " " + evento.getEventResource() + " " + evento.getTimeGenerated());
        }catch (Exception e){
            LOGGER.info("Error: " + e.getMessage());
        }
    }

}
