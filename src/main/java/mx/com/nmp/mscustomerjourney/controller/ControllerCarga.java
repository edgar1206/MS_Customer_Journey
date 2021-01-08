package mx.com.nmp.mscustomerjourney.controller;

import mx.com.nmp.mscustomerjourney.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RestController
@RequestMapping("/customer/event/v1")
public class ControllerCarga {

    @Autowired
    private EventoService eventoService;

    @PostMapping("/log")
    public Boolean addLog(@RequestBody String log, @RequestHeader HttpHeaders headers){
        try{
            String applicationName = Objects.requireNonNull(headers.get("applicationName")).get(0);
            eventoService.recibeLog(log, applicationName);
            return true;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
