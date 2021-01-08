package mx.com.nmp.mscustomerjourney.model.dto;

import java.io.Serializable;

public class ErroresDto implements Serializable {

    private String codigoError;
    private String nombreAplicacion;

    public String getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

    public String getNombreAplicacion() {
        return nombreAplicacion;
    }

    public void setNombreAplicacion(String nombreAplicacion) {
        this.nombreAplicacion = nombreAplicacion;
    }

}
