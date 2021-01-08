package mx.com.nmp.mscustomerjourney.model.dto;

import java.io.Serializable;
import java.util.List;

public class ListaErrores implements Serializable {

    private List<ErroresDto> listaErrores;

    public List<ErroresDto> getListaErrores() {
        return listaErrores;
    }

    public void setListaErrores(List<ErroresDto> listaErrores) {
        this.listaErrores = listaErrores;
    }

}
