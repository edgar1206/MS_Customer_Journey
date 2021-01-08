package mx.com.nmp.mscustomerjourney.model.catalogo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document(collection = "#{@environment.getProperty('indice.aplicaciones.mongo')}")
public class Application implements Serializable {

    @Id
    private String id;
    private String urlAplicacionWeb;
    private String nombreAplicacion;
    private Date ultimaActualizacion;
    private String alertamiento;

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
