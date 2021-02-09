package mx.com.nmp.mscustomerjourney.service;

import mx.com.nmp.mscustomerjourney.model.NR.Evento;
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
import java.util.Date;
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

    private Evento evento;
    private LogsDTO logsDTO;


    @Test
    public void estandarizacionLog() {
        logsDTO = new LogsDTO();
        logsDTO.setTimeStamp(new Date());
        eventoService.estandarizacionLog(logsDTO);
    }
    @Test
    public void procesaLog(){
        evento = new Evento();
        evento.setApplicationName("MiMonte");
        evento.setEventAction("login");
        eventoService.procesaLog(evento);
    }
}