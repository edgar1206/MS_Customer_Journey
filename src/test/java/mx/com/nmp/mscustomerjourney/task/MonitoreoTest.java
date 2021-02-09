package mx.com.nmp.mscustomerjourney.task;

import mx.com.nmp.mscustomerjourney.model.catalogo.Application;
import mx.com.nmp.mscustomerjourney.model.catalogo.Errores;
import mx.com.nmp.mscustomerjourney.model.constant.Constants;
import mx.com.nmp.mscustomerjourney.service.IncidenciaService;
import mx.com.nmp.mscustomerjourney.service.MongoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)

public class MonitoreoTest {

    @InjectMocks
    private Monitoreo monitoreo;

    @Mock
    private Constants constants;

    @Mock
    private IncidenciaService incidenciaService;

    @Mock
    private MongoService mongoService;

    @Mock
    private List<Application> applications;
    @Mock
    private HttpURLConnection httpURLConnection;

    Application app = new Application();

    @Before
    public void setUp() {
        app.setId("0");
        app.setAlertamiento("");
        app.setNombreAplicacion("MiMonte");
        app.setUltimaActualizacion(new Date( 1000 -  24 * 60 * 60 * 1000));

        applications.add(app);
        Mockito.when(constants.getREFRESH_APPLICACION()).thenReturn("3");
        Mockito.when(constants.getREFRESH_ALERT()).thenReturn("300");

    }

    @Test
    public void startTask() {
        monitoreo.startTask();
    }

    @Test
    public void monitoreoException() {
        app.setUrlAplicacionWeb("https://dev1776-frontmimontepagos.mybluemix.net/loginnnnn");
        app.setAlertamiento("4");
        Mockito.when(mongoService.getApplication("0")).thenReturn(app);
        monitoreo.monitoreo(app);

    }
    @Test
    public void monitoreoFalla() throws IOException {
        app.setUrlAplicacionWeb("https://dev1775-frontmimontepagos.mybluemix.net/loginnnnn/hola");
        app.setAlertamiento("5");
        List<Application> listaApplication = new ArrayList<>();
        listaApplication.add(app);
        Mockito.when(mongoService.cargaAplicaciones()).thenReturn(listaApplication);
        monitoreo.cargaCatalogo();
        monitoreo.monitoreo(app);
    }
    @Test
    public void monitoreoFunciona() {
        app.setUrlAplicacionWeb("https://dev1775-frontmimontepagos.mybluemix.net/login");
      //  Mockito.when(mongoService.getApplication("0")).thenReturn(app);
        monitoreo.monitoreo(app);
    }
    @Test//debuggear este
    public void restableceAlertasCatalogoErrores(){
        List<Errores> listaErrores;
        listaErrores = new ArrayList<>();
        listaErrores.add(new Errores("0","1000","Low",new Date(( 1000 -  24 * 60 * 60 * 1000)),"MiMonte","https://prueba"));
        listaErrores.add(new Errores("1","1004","",new Date(),"MiMonte",""));
        listaErrores.add(new Errores("2","1015","",new Date(),"MiMonte",""));
        listaErrores.add(new Errores("3","1017","",new Date(),"MiMonte",""));
        listaErrores.add(new Errores("4","6002","",new Date(),"MiMonte",""));
        listaErrores.add(new Errores("5","6003","",new Date(),"MiMonte",""));
        listaErrores.add(new Errores("6","InternalErrorException","",new Date(),"MiMonte",""));
        listaErrores.add(new Errores("7","UnexpectedLambdaException","",new Date(),"MiMonte",""));
        listaErrores.add(new Errores("8","NMP-30001","",new Date(),"MiMonte",""));
        listaErrores.add(new Errores("9","500","",new Date(),"MiMonte",""));
        listaErrores.add(new Errores("10","<html>r<head><title>502 Bad Gateway</title></head>r<body>r<center><h1>502 Bad Gateway</h1></center>r<hr><center>nginx</center>r</body>r</html>r","",new Date(),"MiMonte",""));
        listaErrores.add(new Errores("11","%{[message][message][descripcion]}","Low",new Date(( 1000 -  24 * 60 * 60 * 1000)),"MiMonte",""));
        Mockito.when(mongoService.getListaErrores()).thenReturn(listaErrores);
        monitoreo.restableceAlertasCatalogoErrores();
    }



        @Test
    public void cargaCatalogo() {

        monitoreo.cargaCatalogo();
    }
}