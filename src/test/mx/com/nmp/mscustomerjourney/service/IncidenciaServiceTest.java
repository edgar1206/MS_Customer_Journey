package mx.com.nmp.mscustomerjourney.service;


import mx.com.nmp.mscustomerjourney.model.NR.Evento;
import mx.com.nmp.mscustomerjourney.model.catalogo.Application;
import mx.com.nmp.mscustomerjourney.model.catalogo.Errores;
import mx.com.nmp.mscustomerjourney.model.constant.Constants;
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

@RunWith(MockitoJUnitRunner.class)
public class IncidenciaServiceTest {

    @InjectMocks
    private IncidenciaService incidenciaService;

    @Mock
    private Constants constants;

    @Mock
    private MongoService mongoService;

    List<Errores> listaErrores;
    List<Application> listaApplication;
    List<Evento> listaEvento;

    String appName = "MiMonte";

    @Before
    public void setUp() {

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
        listaErrores.add(new Errores("9","NMP-3014","",new Date(),appName,""));
        listaErrores.add(new Errores("10","500","",new Date(),appName,""));
        listaErrores.add(new Errores("11","<html>r<head><title>502 Bad Gateway</title></head>r<body>r<center><h1>502 Bad Gateway</h1></center>r<hr><center>nginx</center>r</body>r</html>r","",new Date(),appName,""));
        listaErrores.add(new Errores("12","%{[message][message][descripcion]}","",new Date(),appName,""));
        listaApplication = new ArrayList<>();
        listaApplication.add(new Application("0","https://dev1775-frontmimontepagos.mybluemix.net/login",appName,new Date(),""));

        Evento eventoMidas = new Evento();
        eventoMidas.setEventDescription("{\\\"severidad\\\":\\\"2\\\",\\\"codigoError\\\":\\\"NMP-3005\\\",\\\"descripcionError\\\":\\\"Porcentaje de coincidencia es menor del 85%\\\",\\\"tipoError\\\":\\\"Error de Negocio\\\"}");
        eventoMidas.setEventLevel("ERROR");

        Evento eventoCognito = new Evento();
        eventoCognito.setEventDescription("{\\\"retryDelay\\\":44.44249812346579,\\\"retryable\\\":false,\\\"code\\\":\\\"UserNotFoundException\\\",\\\"requestId\\\":\\\"94eb2d39-ce98-42d2-b2fa-598824ec0d0d\\\",\\\"time\\\":\\\"2020-09-03T20:46:25.030Z\\\",\\\"message\\\":\\\"User does not exist.\\\",\\\"statusCode\\\":400}");
        eventoCognito.setEventLevel("ERROR");

        Evento eventoOAuthError = new Evento();
        eventoOAuthError.setEventDescription("{\\\"error\\\":\\\"ValidationError Need: refreshToken\\\"}");
        eventoOAuthError.setEventLevel("ERROR");

        Evento eventoOpenPay = new Evento();
        eventoOpenPay.setEventDescription("{\\\"description\\\":\\\"The api key or merchant id are invalid\\\",\\\"error_code\\\":1002,\\\"http_code\\\":401,\\\"category\\\":\\\"request\\\"}");
        eventoOpenPay.setEventLevel("ERROR");

        Evento incidencia = new Evento();
        incidencia.setEventLevel("ERROR");
        incidencia.setEventDescription("%{[message][message][descripcion]}");
        incidencia.setApplicationName("MiMonte");

        listaEvento = new ArrayList<>();


        listaEvento.add(eventoMidas);
        listaEvento.add(eventoCognito);
        listaEvento.add(eventoOAuthError);
        listaEvento.add(eventoOpenPay);
        //listaEvento.add(incidencia);



    }

    @Test
    public void categorizar() {

        Mockito.when(mongoService.getListaErrores()).thenReturn(listaErrores);
        Mockito.when(constants.getAPPLICATION_NAME()).thenReturn("MiMonte");
        Mockito.when(constants.getRESOLUTION_TOWER()).thenReturn("Microservicio-L2");
        Mockito.when(constants.getMS_EVENTOS_URL()).thenReturn("");



        listaEvento.forEach(evento -> {
            incidenciaService.categorizar(evento);
        });

    }


}