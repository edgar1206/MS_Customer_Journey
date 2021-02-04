package mx.com.nmp.mscustomerjourney.service;



import mx.com.nmp.mscustomerjourney.model.catalogo.Application;
import mx.com.nmp.mscustomerjourney.model.catalogo.Errores;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)

public class MongoServiceTest {

    @InjectMocks
    private MongoService mongoService;

    Errores errores = new Errores();


    List<Errores> listaErrores;

    @Mock
    List<Application> listapp = new ArrayList<Application>();

    @Before
    public void setUp() {

        mongoService.inicializaCatalogos();

        listaErrores = new ArrayList<>();
        listaErrores.add(new Errores("0","1000","",new Date(),"MiMonte",""));
        listaErrores.add(new Errores("1","1004","",new Date(),"MiMonte",""));

        errores.setAlertamiento("Low");
        errores.setRecurso("Cognito");
        errores.setUltimaActualizacion(new Date());
        errores.setId("1");

        listapp.add(new Application());

    }


    @Test
    public void getListaErrores() {

        Assert.assertNotNull(mongoService.getListaErrores());
    }

    @Test
    public void cargaAplicaciones() {


        Assert.assertNotNull(mongoService.cargaAplicaciones());
    }

    @Test
    public void getApplication() {

        Assert.assertNotNull(mongoService.getApplication("0"));
    }

}