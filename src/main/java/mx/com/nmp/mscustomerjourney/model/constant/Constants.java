package mx.com.nmp.mscustomerjourney.model.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {

    private String RABBIT_HOST;
    private String RABBIT_USERNAME;
    private String RABBIT_IN;
    private String EXCHANGE;
    private String QUEUE_EVENTOS;
    private String QUEUE_NOTIFICACIONES;
    private String QUEUE_EXPERIENCIA;
    private String ROUTING_KEY_EVENTOS;
    private String ROUTING_KEY_NOTIFICACIONES;
    private String ROUTING_KEY_EXPERIENCIA;
    private String MS_EVENTOS_URL;
    private String MS_EVENTOS_INCIDENCIA;
    private String REFRESH_ALERT;
    private String REFRESH_APPLICACION;
    private String TIEMPO_AUMENTA_ALERTAMIENTO;
    private String NEW_RELIC_URL;

    public Constants(

             @Value("${spring.rabbitmq.host}") String RABBIT_HOST,
             @Value("${spring.rabbitmq.username}") String RABBIT_USERNAME,
             @Value("${spring.rabbitmq.password}") String RABBIT_IN,
             @Value("${spring.rabbitmq.exchange}") String EXCHANGE,
             @Value("${spring.rabbitmq.queue.eventos}") String QUEUE_EVENTOS,
             @Value("${spring.rabbitmq.queue.notificaciones}") String QUEUE_NOTIFICACIONES,
             @Value("${spring.rabbitmq.routingkey.eventos}") String ROUTING_KEY_EVENTOS,
             @Value("${spring.rabbitmq.routingkey.notificaciones}") String ROUTING_KEY_NOTIFICACIONES,
             @Value("${ms.eventos.url}") String MS_EVENTOS_URL,
             @Value("${ms.eventos.url.notificacion}") String MS_EVENTOS_URL_ICIDENCIA,
             @Value("${refresh.alert.time.minutos}") String REFRESH_ALERT,
             @Value("${rango.nivel.alertamiento.minutos}") String TIEMPO_AUMENTA_ALERTAMIENTO,
             @Value("${spring.rabbitmq.queue.experiencia}") String QUEUE_EXPERIENCIA,
             @Value("${spring.rabbitmq.routingkey.experiencia}") String ROUTING_KEY_EXPERIENCIA,
             @Value("${refresh.applicacion.time.minutos}") String REFRESH_APPLICACION,
             @Value("${endpoint.newrelic.url}") String NEW_RELIC_URL)

    {

        this.RABBIT_HOST = RABBIT_HOST;
        this.RABBIT_USERNAME = RABBIT_USERNAME;
        this.RABBIT_IN = RABBIT_IN;
        this.EXCHANGE = EXCHANGE;
        this.QUEUE_EVENTOS = QUEUE_EVENTOS;
        this.QUEUE_NOTIFICACIONES = QUEUE_NOTIFICACIONES;
        this.QUEUE_EXPERIENCIA = QUEUE_EXPERIENCIA;
        this.ROUTING_KEY_EVENTOS = ROUTING_KEY_EVENTOS;
        this.ROUTING_KEY_NOTIFICACIONES = ROUTING_KEY_NOTIFICACIONES;
        this.ROUTING_KEY_EXPERIENCIA = ROUTING_KEY_EXPERIENCIA;
        this.MS_EVENTOS_URL = MS_EVENTOS_URL;
        this.MS_EVENTOS_INCIDENCIA = MS_EVENTOS_URL_ICIDENCIA;
        this.REFRESH_ALERT = REFRESH_ALERT;
        this.REFRESH_APPLICACION = REFRESH_APPLICACION;
        this.TIEMPO_AUMENTA_ALERTAMIENTO= TIEMPO_AUMENTA_ALERTAMIENTO;
        this.NEW_RELIC_URL= NEW_RELIC_URL;

    }

    public String getRABBIT_HOST() {
        return RABBIT_HOST;
    }

    public String getRABBIT_USERNAME() {
        return RABBIT_USERNAME;
    }

    public String getRABBIT_IN() {
        return RABBIT_IN;
    }

    public String getEXCHANGE() {
        return EXCHANGE;
    }

    public String getQUEUE_EVENTOS() {
        return QUEUE_EVENTOS;
    }

    public String getQUEUE_NOTIFICACIONES() {
        return QUEUE_NOTIFICACIONES;
    }

    public String getQUEUE_EXPERIENCIA() {
        return QUEUE_EXPERIENCIA;
    }

    public String getROUTING_KEY_EVENTOS() {
        return ROUTING_KEY_EVENTOS;
    }

    public String getROUTING_KEY_NOTIFICACIONES() {
        return ROUTING_KEY_NOTIFICACIONES;
    }

    public String getROUTING_KEY_EXPERIENCIA() {
        return ROUTING_KEY_EXPERIENCIA;
    }

    public String getMS_EVENTOS_URL() {
        return MS_EVENTOS_URL;
    }

    public String getMS_EVENTOS_INCIDENCIA() {
        return MS_EVENTOS_INCIDENCIA;
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
}

