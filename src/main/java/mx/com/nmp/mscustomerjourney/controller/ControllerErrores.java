package mx.com.nmp.mscustomerjourney.controller;

import mx.com.nmp.mscustomerjourney.model.catalogo.Errores;
import mx.com.nmp.mscustomerjourney.model.dto.ListaErrores;
import mx.com.nmp.mscustomerjourney.model.dto.ErroresDto;
import mx.com.nmp.mscustomerjourney.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/customer/catalogue/v1")
public class ControllerErrores {

    @Autowired
    private MongoService mongoService;

    @PostMapping("/error")
    public Boolean agregaError(@RequestBody ErroresDto erroresDto, @RequestHeader HttpHeaders headers) {
        try {
            Errores error = new Errores();
            error.setCodigoError(erroresDto.getCodigoError());
            error.setUltimaActualizacion(new Date());
            error.setAlertamiento("");
            error.setNombreAplicacion(erroresDto.getNombreAplicacion());
            error.setNombreAplicacion(Objects.requireNonNull(headers.get("nombreAplicacion")).get(0));
            mongoService.saveError(error);
            return true;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/errors")
    public Boolean agregaErrores(@RequestBody ListaErrores erroresDtoLista, @RequestHeader HttpHeaders headers) {
        try {
            List<Errores> erroresLista = new ArrayList<>();
            for (ErroresDto erroresDto : erroresDtoLista.getListaErrores()) {
                Errores error = new Errores();
                error.setCodigoError(erroresDto.getCodigoError());
                error.setUltimaActualizacion(new Date());
                error.setAlertamiento("");
                error.setNombreAplicacion(Objects.requireNonNull(headers.get("nombreAplicacion")).get(0));
                erroresLista.add(error);
            }
            mongoService.saveErrores(erroresLista);
            return true;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/errors")
    public List<Errores> getErrores(){
        List<Errores> errores = mongoService.getListaErrores();
        if(errores == null || errores.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No hay Errores registradas");
        }
        return errores;
    }

    @DeleteMapping("/error/{id}")
    public Boolean deleteError(@PathVariable String id){
        try{
            mongoService.deleteError(id);
            return true;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/error/{id}")
    public Boolean putApplication(@PathVariable String id, @RequestBody ErroresDto errorDto){
        try{
            Errores error = mongoService.getError(id);
            error.setNombreAplicacion(errorDto.getNombreAplicacion());
            error.setCodigoError(errorDto.getCodigoError());
            mongoService.saveError(error);
            return true;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
