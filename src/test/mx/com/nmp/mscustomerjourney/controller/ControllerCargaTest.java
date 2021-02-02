package mx.com.nmp.mscustomerjourney.controller;

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

@RunWith(MockitoJUnitRunner.class)

public class ControllerCargaTest {

    @InjectMocks
    private ControllerCarga controllerCarga;

    @Mock
    private EventoService eventoService;

    private LogsDTO logsDto;

    @Before
    public void setUp(){
        logsDto = new LogsDTO();

    }

    @Test
    public void addLog() {

        controllerCarga.addLog(logsDto);
    }

    /*@Test
    public void addLogFail() {
        given(eventoService.recibeLog(logsDto)).will(throw new Exception());
        controllerCarga.addLog(logsDto);
    }*/

    @Test
    public void addLogs() {
        List<LogsDTO> listLogs = new ArrayList<>();
        controllerCarga.addLogs(listLogs);
    }

    /*@Test
    public void addLogsFail() {
        List<LogsDTO> listLogs = new ArrayList<>();
        controllerCarga.addLogs(listLogs);
    }*/
}