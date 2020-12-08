package mx.com.nmp.mscustomerjourney.model.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {

    public static String RABBIT_HOST;
    public static String RABBIT_USERNAME;
    public static String RABBIT_IN;
    public static String EXCHANGE;
    public static String QUEUE_EVENTOS;
    public static String QUEUE_NOTIFICACIONES;
    public static String ROUTING_KEY_EVENTOS;
    public static String ROUTING_KEY_NOTIFICACIONES;

    public static String MS_EVENTOS_URL;
    public static String MS_EVENTOS_INCIDENCIA;

    public Constants(@Value("${spring.rabbitmq.host}") String RABBIT_HOST,
                     @Value("${spring.rabbitmq.username}") String RABBIT_USERNAME,
                     @Value("${spring.rabbitmq.password}") String RABBIT_IN,
                     @Value("${spring.rabbitmq.exchange}") String EXCHANGE,
                     @Value("${spring.rabbitmq.queue.eventos}") String QUEUE_EVENTOS,
                     @Value("${spring.rabbitmq.queue.notificaciones}") String QUEUE_NOTIFICACIONES,
                     @Value("${spring.rabbitmq.routingkey.eventos}") String ROUTING_KEY_EVENTOS,
                     @Value("${spring.rabbitmq.routingkey.notificaciones}") String ROUTING_KEY_NOTIFICACIONES,
                     @Value("${ms.eventos.url}") String MS_EVENTOS_URL,
                     @Value("${ms.eventos.url.notificacion}") String MS_EVENTOS_URL_ICIDENCIA)

    {

        Constants.RABBIT_HOST = RABBIT_HOST;
        Constants.RABBIT_USERNAME = RABBIT_USERNAME;
        Constants.RABBIT_IN = RABBIT_IN;
        Constants.EXCHANGE = EXCHANGE;
        Constants.QUEUE_EVENTOS = QUEUE_EVENTOS;
        Constants.QUEUE_NOTIFICACIONES = QUEUE_NOTIFICACIONES;
        Constants.ROUTING_KEY_EVENTOS = ROUTING_KEY_EVENTOS;
        Constants.ROUTING_KEY_NOTIFICACIONES = ROUTING_KEY_NOTIFICACIONES;

        Constants.MS_EVENTOS_URL = MS_EVENTOS_URL;
        Constants.MS_EVENTOS_INCIDENCIA = MS_EVENTOS_URL_ICIDENCIA;

    }

}

