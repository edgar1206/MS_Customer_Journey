package mx.com.nmp.mscustomerjourney.model.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {

    private String MS_EVENTOS_URL;
    private String REFRESH_ALERT;
    private String REFRESH_APPLICACION;
    private String TIEMPO_AUMENTA_ALERTAMIENTO;
    private String NEW_RELIC_URL;
    private String RESOLUTION_TOWER;
    private String EVENT_TYPE;
    private String APPLICATION_NAME;
    private String APPLICATION_URL;
    private String HEADER;
    private String QUEUE_LOGS;
    private String QUEUE_NOTIFICACIONES;
    private String QUEUE_EXPERIENCIAS;
    private String ROUTING_LOGS;
    private String ROUTING_NOTIFICACIONES;
    private String ROUTING_EXPERIENCIAS;
    private String CONFIGURATION_ELEMENT;
    private String RABBIT_HOST;
    private String RABBIT_PORT;
    private String RABBIT_USERNAME;
    private String RABBIT_SCT;
    private String RABBIT_VIRTUAL_HOST;
    private String RABBIT_SSL_ALGORITHM;

    public Constants(

             @Value("${ms.eventos.url}") String MS_EVENTOS_URL,
             @Value("${refresh.alert.time.minutos}") String REFRESH_ALERT,
             @Value("${rango.nivel.alertamiento.minutos}") String TIEMPO_AUMENTA_ALERTAMIENTO,
             @Value("${refresh.applicacion.time.minutos}") String REFRESH_APPLICACION,
             @Value("${endpoint.newrelic.url}") String NEW_RELIC_URL,
             @Value("${torre.resolucion}") String RESOLUTION_TOWER,
             @Value("${tipo.evento}") String EVENT_TYPE,
             @Value("${nombre.aplicacion}") String APPLICATION_NAME,
             @Value("${url.aplicacion}") String APPLICATION_URL,
             @Value("${rabbitmq.header}") String HEADER,
             @Value("${rabbitmq.queue.logs}") String QUEUE_LOGS,
             @Value("${rabbitmq.queue.notificaciones}") String QUEUE_NOTIFICACIONES,
             @Value("${rabbitmq.queue.experiencias}") String QUEUE_EXPERIENCIAS,
             @Value("${rabbitmq.routing.logs}") String ROUTING_LOGS,
             @Value("${rabbitmq.routing.notificaciones}") String ROUTING_NOTIFICACIONES,
             @Value("${rabbitmq.routing.experiencias}") String ROUTING_EXPERIENCIAS,
             @Value("${configuration.element}") String CONFIGURATION_ELEMENT,
             @Value("${spring.rabbitmq.host}") String RABBIT_HOST,
             @Value("${spring.rabbitmq.port}") String RABBIT_PORT,
             @Value("${spring.rabbitmq.username}") String RABBIT_USERNAME,
             @Value("${spring.rabbitmq.sct}") String RABBIT_SCT,
             @Value("${spring.rabbitmq.virtual-host}") String RABBIT_VIRTUAL_HOST,
             @Value("${spring.rabbitmq.ssl.algorithm}") String RABBIT_SSL_ALGORITHM)

    {

        this.MS_EVENTOS_URL = MS_EVENTOS_URL;
        this.REFRESH_ALERT = REFRESH_ALERT;
        this.REFRESH_APPLICACION = REFRESH_APPLICACION;
        this.TIEMPO_AUMENTA_ALERTAMIENTO = TIEMPO_AUMENTA_ALERTAMIENTO;
        this.NEW_RELIC_URL = NEW_RELIC_URL;
        this.RESOLUTION_TOWER = RESOLUTION_TOWER;
        this.EVENT_TYPE = EVENT_TYPE;
        this.APPLICATION_NAME = APPLICATION_NAME;
        this.APPLICATION_URL = APPLICATION_URL;
        this.HEADER = HEADER;
        this.QUEUE_LOGS = QUEUE_LOGS;
        this.QUEUE_EXPERIENCIAS = QUEUE_EXPERIENCIAS;
        this.QUEUE_NOTIFICACIONES = QUEUE_NOTIFICACIONES;
        this.ROUTING_LOGS = ROUTING_LOGS;
        this.ROUTING_EXPERIENCIAS = ROUTING_EXPERIENCIAS;
        this.ROUTING_NOTIFICACIONES = ROUTING_NOTIFICACIONES;
        this.CONFIGURATION_ELEMENT = CONFIGURATION_ELEMENT;
        this.RABBIT_HOST = RABBIT_HOST;
        this.RABBIT_PORT = RABBIT_PORT;
        this.RABBIT_VIRTUAL_HOST = RABBIT_VIRTUAL_HOST;
        this.RABBIT_USERNAME = RABBIT_USERNAME;
        this.RABBIT_SCT = RABBIT_SCT;
        this.RABBIT_SSL_ALGORITHM = RABBIT_SSL_ALGORITHM;

    }

    public String getMS_EVENTOS_URL() {
        return MS_EVENTOS_URL;
    }

    public String getREFRESH_ALERT() {
        return REFRESH_ALERT;
    }

    public String getTIEMPO_AUMENTA_ALERTAMIENTO() {
        return TIEMPO_AUMENTA_ALERTAMIENTO;
    }

    public String getREFRESH_APPLICACION() {
        return REFRESH_APPLICACION;
    }

    public String getNEW_RELIC_URL() {
        return NEW_RELIC_URL;
    }

    public String getRESOLUTION_TOWER() {
        return RESOLUTION_TOWER;
    }

    public String getEVENT_TYPE() {
        return EVENT_TYPE;
    }

    public String getAPPLICATION_NAME() {
        return APPLICATION_NAME;
    }

    public String getAPPLICATION_URL() {
        return APPLICATION_URL;
    }

    public String getHEADER() {
        return HEADER;
    }

    public String getQUEUE_LOGS() {
        return QUEUE_LOGS;
    }

    public String getQUEUE_NOTIFICACIONES() {
        return QUEUE_NOTIFICACIONES;
    }

    public String getQUEUE_EXPERIENCIAS() {
        return QUEUE_EXPERIENCIAS;
    }

    public String getROUTING_LOGS() {
        return ROUTING_LOGS;
    }

    public String getROUTING_NOTIFICACIONES() {
        return ROUTING_NOTIFICACIONES;
    }

    public String getROUTING_EXPERIENCIAS() {
        return ROUTING_EXPERIENCIAS;
    }

    public String getCONFIGURATION_ELEMENT() {
        return CONFIGURATION_ELEMENT;
    }

    public String getRABBIT_HOST() {
        return RABBIT_HOST;
    }

    public String getRABBIT_PORT() {
        return RABBIT_PORT;
    }

    public String getRABBIT_USERNAME() {
        return RABBIT_USERNAME;
    }

    public String getRABBIT_SCT() {
        return RABBIT_SCT;
    }

    public String getRABBIT_VIRTUAL_HOST() {
        return RABBIT_VIRTUAL_HOST;
    }

    public String getRABBIT_SSL_ALGORITHM() {
        return RABBIT_SSL_ALGORITHM;
    }

}

