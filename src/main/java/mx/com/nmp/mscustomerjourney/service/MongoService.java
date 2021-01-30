package mx.com.nmp.mscustomerjourney.service;

import mx.com.nmp.mscustomerjourney.model.catalogo.Application;
import mx.com.nmp.mscustomerjourney.model.catalogo.Errores;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class MongoService {

    @Value("${url.aplicacion}")
    private String appUrl;

    @Value("${nombre.aplicacion}")
    private String appName;

    private List<Errores> listaErrores;

    private List<Application> listaApplication;

    @Bean
    private void inicializaCatalogos(){
        listaErrores = new ArrayList<>();
        listaErrores.add(new Errores("0","1000","",new Date(),appName,""));
        listaErrores.add(new Errores("1","1004","",new Date(),appName,""));
        listaErrores.add(new Errores("2","1015","",new Date(),appName,""));
        listaErrores.add(new Errores("3","1017","",new Date(),appName,""));
        listaErrores.add(new Errores("4","6002","",new Date(),appName,""));
        listaErrores.add(new Errores("5","6003","",new Date(),appName,""));
        listaErrores.add(new Errores("6","InternalErrorException","",new Date(),appName,""));
        listaErrores.add(new Errores("7","UnexpectedLambdaException","",new Date(),appName,""));
        listaErrores.add(new Errores("8","NMP-30001","",new Date(),appName,""));
        listaErrores.add(new Errores("9","500","",new Date(),appName,""));
        listaErrores.add(new Errores("10","<html>r<head><title>502 Bad Gateway</title></head>r<body>r<center><h1>502 Bad Gateway</h1></center>r<hr><center>nginx</center>r</body>r</html>r","",new Date(),appName,""));
        listaErrores.add(new Errores("11","%{[message][message][descripcion]}","",new Date(),appName,""));
        listaApplication = new ArrayList<>();
        listaApplication.add(new Application("0",appUrl,appName,new Date(),""));
    }

    public void cargaCatalogo(){ }

    public List<Errores> getListaErrores() {
        return listaErrores;
    }

    public List<Application> cargaAplicaciones(){
        return listaApplication;
    }

    public Application getApplication(String id){
        return listaApplication.get(Integer.parseInt(id));
    }


}
