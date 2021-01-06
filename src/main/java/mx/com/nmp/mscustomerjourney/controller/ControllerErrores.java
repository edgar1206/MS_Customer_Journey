package mx.com.nmp.mscustomerjourney.controller;

import mx.com.nmp.mscustomerjourney.model.catalogo.Errores;
import mx.com.nmp.mscustomerjourney.model.dto.ListaErrores;
import mx.com.nmp.mscustomerjourney.model.dto.ErroresDto;
import mx.com.nmp.mscustomerjourney.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/catalogo")
public class ControllerErrores {

    @Autowired
    private MongoService mongoService;

    @PostMapping("/error")
    public ResponseEntity<?> agregaError(@RequestBody ErroresDto erroresDto){
       Errores error = new Errores();
       error.setCodigoError(erroresDto.getCodigoError());
       error.setUltimaActualizacion(new Date());
       error.setAlertamiento("");
       mongoService.saveError(error);
       return ResponseEntity.ok().build();
    }
    @PostMapping("/errores")
    public ResponseEntity<?> agregaErrores(@RequestBody ListaErrores erroresDtoLista){
        List<Errores> erroresLista = new ArrayList<>();
        for (ErroresDto erroresDto: erroresDtoLista.getListaErrores()) {
            Errores error = new Errores();
            error.setCodigoError(erroresDto.getCodigoError());
            error.setUltimaActualizacion(new Date());
            error.setAlertamiento("");
            erroresLista.add(error);
        }
        mongoService.saveErrores(erroresLista);
        return ResponseEntity.ok().build();
    }
}
