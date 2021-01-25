package mx.com.nmp.mscustomerjourney.task;

import mx.com.nmp.mscustomerjourney.model.NR.Evento;
import mx.com.nmp.mscustomerjourney.model.catalogo.Application;
import mx.com.nmp.mscustomerjourney.model.catalogo.Errores;
import mx.com.nmp.mscustomerjourney.model.constant.Constants;
import mx.com.nmp.mscustomerjourney.service.IncidenciaService;
import mx.com.nmp.mscustomerjourney.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.logging.Logger;

@Component
public class Monitoreo {

    @Autowired
    private Constants constants;

    @Autowired
    private IncidenciaService incidenciaService;

    @Autowired
    private MongoService mongoService;

    private List<Application> applications;

    private static final Logger LOGGER = Logger.getLogger(Monitoreo.class.getName());

    public Monitoreo() {
        this.applications = new ArrayList<>();
    }

    @Scheduled(cron = "${monitoreo.aplicaciones.cron.time}")
    public void startTask(){
        if( applications == null){
            return;
        }
        applications.stream().parallel().forEach(this::monitoreo);
    }

    @Async
    public void monitoreo(Application application){
        try {
            URL siteURL = new URL(application.getUrlAplicacionWeb());
            HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(2000);
            connection.connect();
            int code = connection.getResponseCode();
            if (code != 200) {
                LOGGER.info("error no responde el sitio web -> " + application.getUrlAplicacionWeb());
                nivelAlertamientoSitioWeb(application);
            }else{
                verificaFuncionalidadSitioWeb(application);
            }
        } catch (Exception e) {
            LOGGER.info("error no responde el sitio web -> " + application.getUrlAplicacionWeb());
            nivelAlertamientoSitioWeb(application);
        }
    }

    @Async
    @Scheduled(cron = "${carga.catalogos.cron.time}")
    public void cargaCatalogo(){
        applications = mongoService.cargaAplicaciones();
        mongoService.cargaCatalogo();
    }

    @Scheduled(cron = "${restablece.catalogo.errores.cron.time}")
    private void restableceAlertasCatalogoErrores(){
        List<Errores> erroresLista = new ArrayList<>();
        erroresLista = mongoService.getListaErrores();
        if(erroresLista == null){
            return;
        }
        for (Errores error: erroresLista) {
            long tiempoMs = new Date().getTime() - error.getUltimaActualizacion().getTime();
            long diferenciaMinutos = tiempoMs / (1000 * 60 );
            if(diferenciaMinutos >= Integer.parseInt(constants.getREFRESH_ALERT()) && !error.getAlertamiento().isEmpty() && estatusFuncional(error.getRecurso())){
                //notificaServicioFuncional(error);
                error.setAlertamiento("");
                error.setRecurso("");
                error.setUltimaActualizacion(new Date());
                mongoService.saveError(error);
            }
        }
    }

    private Boolean estatusFuncional(String url){
        if(!url.contains("https://")){
            return true;
        }
        try {
            String[] array = url.split("/");
            String[] valorPuerto = new String[2];
            StringBuilder fullPath = new StringBuilder();
            for ( int i = 0; i < array.length ; i++ ) {
                if( i > 2 && i < array.length -1 ){
                    fullPath.append(array[i]).append("/");
                }
                valorPuerto = array[i].split(":");
            }
            String protocol = array[0];
            String host = array[2];
            String puerto = valorPuerto[1];
            String recurso = protocol + "//" + host + ":" + puerto + fullPath + valorPuerto[0];
            URL siteURL = new URL(recurso);
            HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(2000);
            connection.connect();
            return connection.getResponseCode() != 500;
        } catch (Exception e) {
            return false;
        }
    }

    private void generaIncidenciaSitioWeb(Application application){
        Evento evento = new Evento();
        evento.setTimeGenerated(new Date());
        evento.setEventDescription("El sitio web no responde");
        evento.setEventLevel("Error");
        evento.setIdEvent(UUID.randomUUID().toString());
        evento.setEventResource(application.getUrlAplicacionWeb());
        evento.setTimeGenerated(application.getUltimaActualizacion());
        evento.setSeverity("CRITICAL");
        evento.setEventType(application.getNombreAplicacion());
        evento.setEventAction("");
        evento.setEventCategory("");
        evento.setEventPhase("");
        incidenciaService.generaIncidencia(evento);
    }

    private void nivelAlertamientoSitioWeb(Application application){
        application = mongoService.getApplication(application.getId());
        if(application.getAlertamiento().isEmpty()){
            application.setAlertamiento("1");
        }else{
            int num = Integer.parseInt(application.getAlertamiento());
            num+=1;
            if(num == 5){
                application.setUltimaActualizacion(new Date());
                generaIncidenciaSitioWeb(application);
            }
            application.setAlertamiento(String.valueOf(num));
        }
        mongoService.saveApplication(application);
    }

    private void verificaFuncionalidadSitioWeb(Application application){
        long tiempoMs = new Date().getTime() - application.getUltimaActualizacion().getTime();
        long diferenciaMinutos = tiempoMs / (1000 * 60 );
        if(diferenciaMinutos >= Integer.parseInt(constants.getREFRESH_APPLICACION()) && !application.getAlertamiento().isEmpty()){
            applications.forEach(applicationweb -> {
                if (applicationweb.getId().equalsIgnoreCase(application.getId())){
                    applicationweb.setAlertamiento("");
                    applicationweb.setUltimaActualizacion(new Date());
                    mongoService.saveApplication(applicationweb);
                    //notificaSitioWebFuncional(applicationweb);
                }
            });
        }
    }

    private void notificaSitioWebFuncional(Application application){
        System.out.println("La app " + application.getNombreAplicacion() + " restablecida y funcional");
        /*TODO
        *  enviar notificacion a service now
        * */
    }

    private void notificaServicioFuncional(Errores error){
        /*TODO
         *  enviar notificacion a service now
         * */
        System.out.println("Se ha restablecido el recurso " + error.getRecurso() + " de la app " + error.getNombreAplicacion());
    }

}