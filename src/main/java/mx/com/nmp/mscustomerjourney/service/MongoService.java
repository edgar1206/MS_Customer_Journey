package mx.com.nmp.mscustomerjourney.service;

import mx.com.nmp.mscustomerjourney.model.catalogo.Errores;
import mx.com.nmp.mscustomerjourney.repository.ErroresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MongoService {
    @Autowired
    private ErroresRepository erroresRepository;

    private List<Errores> listaErrores;

    //@Scheduled(cron = "")
    public void cargaCatalogo(){
        listaErrores = erroresRepository.findAll();
    }

    public List<Errores> getListaErrores() {
        return listaErrores;
    }

    public void setListaErrores(List<Errores> listaErrores) {
        this.listaErrores = listaErrores;
    }

    public void saveError(Errores error){
        erroresRepository.save(error);
    }
}
