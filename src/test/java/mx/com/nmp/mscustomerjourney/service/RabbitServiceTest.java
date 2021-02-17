package mx.com.nmp.mscustomerjourney.service;

import mx.com.nmp.mscustomerjourney.model.NR.Evento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class RabbitServiceTest {
    @InjectMocks
    private RabbitService rabbitService;

    private Evento evento;

    @BeforeEach
    void setUp(){
        evento = new Evento();
        evento.setEventDescription("{\"retryDelay\":44.44249812346579,\"retryable\":false,\"code\":\"UserNotFoundException\",\"requestId\":\"94eb2d39-ce98-42d2-b2fa-598824ec0d0d\",\"time\":\"2020-09-03T20:46:25.030Z\",\"message\":\"User does not exist.\",\"statusCode\":400}");
        evento.setEventLevel("ERROR");
    }

    @Test
    void enviaExperiencia() {
        rabbitService.enviaExperiencia(evento);
    }

    @Test
    void enviaNotificacion() {
        rabbitService.enviaNotificacion(evento);
    }
}