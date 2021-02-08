package mx.com.nmp.mscustomerjourney.model.catalogo;

import java.io.Serializable;
import java.util.Date;

public class Application implements Serializable {

    private String id;
    private String urlAplicacionWeb;
    private String nombreAplicacion;
    private Date ultimaActualizacion;
    private String alertamiento;

    public Application(String id, String urlAplicacionWeb, String nombreAplicacion, Date ultimaActualizacion, String alertamiento) {
        this.id = id;
        this.urlAplicacionWeb = urlAplicacionWeb;
        this.nombreAplicacion = nombreAplicacion;
        this.ultimaActualizacion = ultimaActualizacion;
        this.alertamiento = alertamiento;
    }

    public Application(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlAplicacionWeb() {
        return urlAplicacionWeb;
    }

    public void setUrlAplicacionWeb(String urlAplicacionWeb) {
        this.urlAplicacionWeb = urlAplicacionWeb;
    }

    public String getNombreAplicacion() {
        return nombreAplicacion;
    }

    public void setNombreAplicacion(String nombreAplicacion) {
        this.nombreAplicacion = nombreAplicacion;
    }

    public Date getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public void setUltimaActualizacion(Date ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }

    public String getAlertamiento() {
        return alertamiento;
    }

    public void setAlertamiento(String alertamiento) {
        this.alertamiento = alertamiento;
    }
}
