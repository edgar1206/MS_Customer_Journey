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

    public Constants(

             @Value("${ms.eventos.url}") String MS_EVENTOS_URL,
             @Value("${refresh.alert.time.minutos}") String REFRESH_ALERT,
             @Value("${rango.nivel.alertamiento.minutos}") String TIEMPO_AUMENTA_ALERTAMIENTO,
             @Value("${refresh.applicacion.time.minutos}") String REFRESH_APPLICACION,
             @Value("${endpoint.newrelic.url}") String NEW_RELIC_URL,
             @Value("${torre.resolucion}") String RESOLUTION_TOWER,
             @Value("${tipo.evento}") String EVENT_TYPE,
             @Value("${nombre.aplicacion}") String APPLICATION_NAME,
             @Value("${url.aplicacion}") String APPLICATION_URL)

    {

        this.MS_EVENTOS_URL = MS_EVENTOS_URL;
        this.REFRESH_ALERT = REFRESH_ALERT;
        this.REFRESH_APPLICACION = REFRESH_APPLICACION;
        this.TIEMPO_AUMENTA_ALERTAMIENTO= TIEMPO_AUMENTA_ALERTAMIENTO;
        this.NEW_RELIC_URL= NEW_RELIC_URL;
        this.RESOLUTION_TOWER=RESOLUTION_TOWER;
        this.EVENT_TYPE = EVENT_TYPE;
        this.APPLICATION_NAME=APPLICATION_NAME;
        this.APPLICATION_URL=APPLICATION_URL;

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
}

