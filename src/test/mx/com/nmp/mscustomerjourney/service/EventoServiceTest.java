package mx.com.nmp.mscustomerjourney.service;

import mx.com.nmp.mscustomerjourney.model.constant.Constants;
import mx.com.nmp.mscustomerjourney.model.log.LogsDTO;
import mx.com.nmp.mscustomerjourney.service.EventoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class EventoServiceTest {

    @InjectMocks
    private EventoService eventoService;

    @Mock
    private Constants constants;

    @Mock
    private IncidenciaService incidenciaService;

    private LogsDTO logsDto;

    @Before
    public void setUp(){
        logsDto = new LogsDTO();

    }

    @Test
    public void recibeLog() {
        when(constants.getMS_EVENTOS_URL()).thenReturn("https://dev1775-ms-eventos.apps.tas.nmp.com.mx/event/trace/v1/event");
        eventoService.recibeLog(logsDto);
    }
}