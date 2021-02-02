package mx.com.nmp.mscustomerjourney.service;

import com.google.gson.Gson;
import mx.com.nmp.mscustomerjourney.model.NR.Evento;
import mx.com.nmp.mscustomerjourney.model.catalogo.Errores;
import mx.com.nmp.mscustomerjourney.model.constant.Constants;
import mx.com.nmp.mscustomerjourney.modelError.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.net.ConnectException;
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
        if(evento.getEventLevel().equalsIgnoreCase("Error") || evento.getEventLevel().equalsIgnoreCase("Fatal")){
            getCodigoError(evento);
        }
    }

    private void getCodigoError (Evento evento){
        Gson gson = new Gson();
        try{
            MidasError midasError = gson.fromJson(evento.getEventDescription(), MidasError.class);
        }catch (Exception e){
            LOGGER.info(e.getMessage());
            verificaError(evento.getEventDescription(), evento);
            return;
        }
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
        OpenPayError2 openPayError2 = gson.fromJson(evento.getEventDescription(), OpenPayError2.class);
        if(openPayError2.getCode() != null){
            verificaError(openPayError2.getCode(),evento);
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
                }else if( diferenciaMinutos >= Integer.parseInt(constants.getTIEMPO_AUMENTA_ALERTAMIENTO())&& error.getAlertamiento().equalsIgnoreCase("High")){
                    error.setAlertamiento("Critical");
                    guardaAlertamiento(error, evento);
                }
            }
        }
    }

    public void guardaAlertamiento(Errores error, Evento evento){
        if(evento.getApplicationName().equalsIgnoreCase(error.getNombreAplicacion())){
            error.setUltimaActualizacion(new Date());
            error.setRecurso(evento.getEventResource());
            evento.setSeverity(error.getAlertamiento());
            evento.setResolutionTower(constants.getRESOLUTION_TOWER());
            evento.setApplicationName(constants.getAPPLICATION_NAME());
            evento.setConfigurationElement("Elemento configuracion");
            LOGGER.info("Incidencia generada error: " + error.getCodigoError());
            generaIncidencia(evento);
        }
    }

    @Async
    public void generaIncidencia(Evento evento) {
        try{
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Evento> requestEvento = new HttpEntity<>(evento);
            LOGGER.info("Enviando incidencia...");
            HttpHeaders headers= new HttpHeaders();
            headers.set("Content-Type","application/json");
            headers.set("X-Insert-Key","NRII-GZKVjLai0OwkBWFqNuybOm_d4v7m2oaW");
            headers.set("Content-Encoding","gzip");
            HttpEntity<Evento> request = new HttpEntity<>(evento,headers);
            restTemplate.postForEntity(constants.getMS_EVENTOS_URL(),requestEvento,String.class);
            ResponseEntity<String> respuestaNewRelic = restTemplate.postForEntity(constants.getNEW_RELIC_URL(),request,String.class);
            LOGGER.info("Codigo respuesta New Relic " + respuestaNewRelic.getStatusCode());
        }catch (HttpClientErrorException | ResourceAccessException e){
            LOGGER.info("No se pudo enviar la incidencia");
            LOGGER.info("Error: " + e.getMessage());
        }
    }

}
