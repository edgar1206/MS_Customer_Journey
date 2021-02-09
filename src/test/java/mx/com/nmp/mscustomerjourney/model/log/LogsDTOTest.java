package mx.com.nmp.mscustomerjourney.model.log;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class LogsDTOTest {
    @InjectMocks
    private LogsDTO logsDTO;

    @Test
    void getCategoryName() {
        logsDTO.getCategoryName();
    }

    @Test
    void setCategoryName() {
        logsDTO.setCategoryName("prueba");
    }

    @Test
    void getStartTime() {
        logsDTO.getStartTime();
    }

    @Test
    void setStartTime() {
        logsDTO.setStartTime(new Date());
    }

    @Test
    void getRecurso() {
        logsDTO.getRecurso();
    }

    @Test
    void setRecurso() {
        logsDTO.setRecurso("prueba");
    }

    @Test
    void getFase() {
        logsDTO.getFase();
    }

    @Test
    void setFase() {
        logsDTO.setFase("prueba");
    }

    @Test
    void getAccion() {
        logsDTO.getAccion();
    }

    @Test
    void setAccion() {
        logsDTO.setAccion("prueba");
    }

    @Test
    void getDescripcion() {
        logsDTO.getDescripcion();
    }

    @Test
    void setDescripcion() {
        logsDTO.setDescripcion("prueba");
    }

    @Test
    void getVersion() {
        logsDTO.getVersion();
    }

    @Test
    void setVersion() {
        logsDTO.setVersion("prueba");
    }

    @Test
    void getLevel() {
        logsDTO.getLevel();
    }

    @Test
    void setLevel() {
        logsDTO.setLevel("prueba");
    }

    @Test
    void getTimeStamp() {
        logsDTO.getTimeStamp();
    }

    @Test
    void setTimeStamp() {
        logsDTO.setTimeStamp(new Date());
    }
}