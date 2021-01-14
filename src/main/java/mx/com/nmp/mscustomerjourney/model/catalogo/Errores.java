package mx.com.nmp.mscustomerjourney.model.catalogo;
/*
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
*/
import java.util.Date;

//@Document(collection = "#{@environment.getProperty('indice.errores.mongo')}")
public class Errores {

  //  @Id
    private String id;
    private String codigoError;
    private String alertamiento;
    private Date ultimaActualizacion;
    private String nombreAplicacion;
    private String recurso;

    public Errores(String id, String codigoError, String alertamiento, Date ultimaActualizacion, String nombreAplicacion, String recurso) {
        this.codigoError = codigoError;
        this.alertamiento = alertamiento;
        this.ultimaActualizacion = ultimaActualizacion;
        this.nombreAplicacion = nombreAplicacion;
        this.recurso = recurso;
        this.id = id;
    }

    public Errores(){}

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

    public String getNombreAplicacion() {
        return nombreAplicacion;
    }

    public void setNombreAplicacion(String nombreAplicacion) {
        this.nombreAplicacion = nombreAplicacion;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

}
