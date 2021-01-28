package mx.com.nmp.mscustomerjourney.model.log;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class LogsDTO {

    private String categoryName;
    private Date startTime;
    @JsonProperty("message.recurso")
    private String recurso;
    @JsonProperty("message.fase")
    private String fase;
    @JsonProperty("message.accion")
    private String accion;
    @JsonProperty("message.descripcion")
    private String descripcion;
    @JsonProperty("@version")
    private String version;
    private String level;
    @JsonProperty("@timestamp")
    private Date timeStamp;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

}
