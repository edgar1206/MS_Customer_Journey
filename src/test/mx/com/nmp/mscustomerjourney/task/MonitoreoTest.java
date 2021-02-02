package mx.com.nmp.mscustomerjourney.task;

import mx.com.nmp.mscustomerjourney.model.catalogo.Application;
import mx.com.nmp.mscustomerjourney.model.constant.Constants;
import mx.com.nmp.mscustomerjourney.service.IncidenciaService;
import mx.com.nmp.mscustomerjourney.service.MongoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

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

    Application app = new Application();

    @Before
    public void setUp() {


        app.setId("0");
        app.setAlertamiento("Low");
        app.setNombreAplicacion("MiMonte");
        app.setUltimaActualizacion(new Date());
        app.setUrlAplicacionWeb("https://dev1775-ms-eventos.apps.tas.nmp.com.mx/event/trace/v1/event");
    }

    @Test
    public void startTask() {
        monitoreo.startTask();
    }

    @Test
    public void monitoreo() {

       monitoreo.monitoreo(app);

    }

    @Test
    public void cargaCatalogo() {

        monitoreo.cargaCatalogo();
    }
}