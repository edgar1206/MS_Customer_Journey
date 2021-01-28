package mx.com.nmp.mscustomerjourney.controller;

import mx.com.nmp.mscustomerjourney.model.log.LogsDTO;
import mx.com.nmp.mscustomerjourney.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/customerJourney/v1")
public class ControllerCarga {

    @Autowired
    private EventoService eventoService;

    @PostMapping("/event")
    public Boolean addLog(@RequestBody LogsDTO event){
        try{
            eventoService.recibeLog(event);
            return true;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    @PostMapping("/events")
    public Boolean addLogs(@RequestBody List<LogsDTO> events){
        try{
            events.forEach(event -> {
                eventoService.recibeLog(event);
            });
            return true;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
