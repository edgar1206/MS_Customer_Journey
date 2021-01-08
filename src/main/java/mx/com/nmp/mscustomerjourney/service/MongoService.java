package mx.com.nmp.mscustomerjourney.service;

import mx.com.nmp.mscustomerjourney.model.catalogo.Application;
import mx.com.nmp.mscustomerjourney.model.catalogo.Errores;
import mx.com.nmp.mscustomerjourney.repository.ApplicationRepository;
import mx.com.nmp.mscustomerjourney.repository.ErroresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MongoService {

    @Autowired
    private ErroresRepository erroresRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    private List<Errores> listaErrores;

    public void cargaCatalogo(){
        listaErrores = erroresRepository.findAll();
    }

    public List<Errores> getListaErrores() {
        return listaErrores;
    }

    public void saveError(Errores error){
        erroresRepository.save(error);
    }

    public void saveErrores(List<Errores> listaErrores){
        erroresRepository.saveAll(listaErrores);
    }

    public void saveApplication(Application application){
        applicationRepository.save(application);
    }

    public List<Application> cargaAplicaciones(){
        return applicationRepository.findAll();
    }

    public Application getApplication(String id){
        return applicationRepository.findById(id).get();
    }

    public void deleteAplication(String id){
        applicationRepository.deleteById(id);
    }

    public void deleteError(String id){
        erroresRepository.deleteById(id);
    }

    public Errores getError(String id){
        return erroresRepository.findById(id).get();
    }

}
