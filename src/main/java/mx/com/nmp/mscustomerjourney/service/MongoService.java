package mx.com.nmp.mscustomerjourney.service;

import mx.com.nmp.mscustomerjourney.model.catalogo.Application;
import mx.com.nmp.mscustomerjourney.model.catalogo.Errores;
/*import mx.com.nmp.mscustomerjourney.repository.ApplicationRepository;
import mx.com.nmp.mscustomerjourney.repository.ErroresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;*/
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class MongoService {

    //@Autowired
    //private ErroresRepository erroresRepository;

    //@Autowired
    //private ApplicationRepository applicationRepository;

    private List<Errores> listaErrores;

    private List<Application> listaApplication;

    public MongoService(){
        listaErrores = new ArrayList<>();
        listaErrores.add(new Errores("0","1000","",new Date(),"MiMonte",""));
        listaErrores.add(new Errores("1","1004","",new Date(),"MiMonte",""));
        listaErrores.add(new Errores("2","1015","",new Date(),"MiMonte",""));
        listaErrores.add(new Errores("3","1017","",new Date(),"MiMonte",""));
        listaErrores.add(new Errores("4","6002","",new Date(),"MiMonte",""));
        listaErrores.add(new Errores("5","6003","",new Date(),"MiMonte",""));
        listaErrores.add(new Errores("6","InternalErrorException","",new Date(),"MiMonte",""));
        listaErrores.add(new Errores("7","UnexpectedLambdaException","",new Date(),"MiMonte",""));
        listaErrores.add(new Errores("8","NMP-30001","",new Date(),"MiMonte",""));
        listaErrores.add(new Errores("9","NMP-3014","",new Date(),"MiMonte",""));
        listaErrores.add(new Errores("10","500","",new Date(),"MiMonte",""));
        listaErrores.add(new Errores("11","<html>r<head><title>502 Bad Gateway</title></head>r<body>r<center><h1>502 Bad Gateway</h1></center>r<hr><center>nginx</center>r</body>r</html>r","",new Date(),"MiMonte",""));
        listaErrores.add(new Errores("12","%{[message][message][descripcion]}","",new Date(),"MiMonte",""));

        listaApplication = new ArrayList<>();
        listaApplication.add(new Application("0","https://dev1775-frontmimontepagos.mybluemix.net/login","MiMonte",new Date(),""));
    }

    public void cargaCatalogo(){ }

    public List<Errores> getListaErrores() {
        return listaErrores;
    }

    public void saveError(Errores error){
        listaErrores.get(Integer.parseInt(error.getId())).setRecurso(error.getRecurso());
        listaErrores.get(Integer.parseInt(error.getId())).setAlertamiento(error.getAlertamiento());
        listaErrores.get(Integer.parseInt(error.getId())).setUltimaActualizacion(error.getUltimaActualizacion());
    }

    public void saveErrores(List<Errores> listaErrores){
        //erroresRepository.saveAll(listaErrores);
        this.listaErrores.addAll(listaErrores);
    }

    public void saveApplication(Application application){
        //applicationRepository.save(application);
        application.setId(String.valueOf(listaApplication.size()+1));
        listaApplication.add(application);
    }

    public List<Application> cargaAplicaciones(){
        //return applicationRepository.findAll();
        return listaApplication;
    }

    public Application getApplication(String id){
        //return applicationRepository.findById(id).get();
        return listaApplication.get(Integer.parseInt(id));
    }

    public void deleteAplication(String id){
        //applicationRepository.deleteById(id);
        listaApplication.remove(Integer.parseInt(id));
    }

    public void deleteError(String id){
        //erroresRepository.deleteById(id);
        listaErrores.remove(Integer.parseInt(id));
    }

    public Errores getError(String id){
        //return erroresRepository.findById(id).get();
        return listaErrores.get(Integer.parseInt(id));
    }

}
