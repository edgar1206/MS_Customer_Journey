package mx.com.nmp.mscustomerjourney.model.catalogo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "#{@environment.getProperty('indice.mongo')}")
public class Errores {
    @Id
    private String id;
    private String codigoError;
    private String alertamiento;
    private Date ultimaActualizacion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

    public String getAlertamiento() {
        return alertamiento;
    }

    public void setAlertamiento(String alertamiento) {
        this.alertamiento = alertamiento;
    }

    public Date getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public void setUltimaActualizacion(Date ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }
}
