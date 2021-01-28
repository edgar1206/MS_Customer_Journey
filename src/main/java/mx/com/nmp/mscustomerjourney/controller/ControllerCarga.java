package mx.com.nmp.mscustomerjourney.controller;

import mx.com.nmp.mscustomerjourney.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RestController
@RequestMapping("/customerJourney/v1")
public class ControllerCarga {

    @Autowired
    private EventoService eventoService;

    @PostMapping("/event")
    public Boolean addLog(@RequestBody String event){
        try{
            eventoService.recibeLog(event);
            return true;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
