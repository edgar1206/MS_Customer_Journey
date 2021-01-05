package mx.com.nmp.mscustomerjourney.controller;

import mx.com.nmp.mscustomerjourney.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monitor/v1")
public class ControllerCarga {

    @Autowired
    private EventoService eventoService;

    @PostMapping("/log")
    public ResponseEntity<?> addLog(@RequestBody String log){
        eventoService.recibeLog(log);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/mensajeZip",params = "mensaje")
    public String getZip(@RequestParam String mensaje){

        return mensaje;
    }

}
