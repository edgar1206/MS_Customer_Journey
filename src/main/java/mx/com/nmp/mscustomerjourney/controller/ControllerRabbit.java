package mx.com.nmp.mscustomerjourney.controller;

import mx.com.nmp.mscustomerjourney.model.log.LogsDTO;
import mx.com.nmp.mscustomerjourney.service.RabbitSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rabbit/v1")
public class ControllerRabbit {

    @Autowired
    private RabbitSender rabbitSender;

    @PostMapping("/sendLog")
    public ResponseEntity<?> sendLog(@RequestBody String log){
        rabbitSender.enviaLog(log);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/mensajeZip",params = "mensaje")
    public String getZip(@RequestParam String mensaje){

        return mensaje;
    }

}
