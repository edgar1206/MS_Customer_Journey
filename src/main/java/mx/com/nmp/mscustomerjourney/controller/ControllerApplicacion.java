package mx.com.nmp.mscustomerjourney.controller;

import mx.com.nmp.mscustomerjourney.model.catalogo.Application;
import mx.com.nmp.mscustomerjourney.model.dto.ApplicationDto;
import mx.com.nmp.mscustomerjourney.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/customer/application/v1")
public class ControllerApplicacion {

    @Autowired
    private MongoService mongoService;

    @PostMapping("/site")
    public Boolean addApplication(@RequestBody ApplicationDto applicationDto){
        try{
            Application application = new Application();
            application.setNombreAplicacion(applicationDto.getApplicationName());
            application.setUrlAplicacionWeb(applicationDto.getUrlApplicationWeb());
            application.setUltimaActualizacion(new Date());
            application.setAlertamiento("");
            mongoService.saveApplication(application);
            return true;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/sites")
    public List<Application> getApplications(){
        List<Application> applications = mongoService.cargaAplicaciones();
        if(applications == null || applications.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No hay aplicaciones registradas");
        }
        return applications;
    }

    @DeleteMapping("/site/{id}")
    public Boolean deleteApplication(@PathVariable String id){
        try{
            mongoService.deleteAplication(id);
            return true;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/site/{id}")
    public Boolean putApplication(@PathVariable String id, @RequestBody ApplicationDto applicationDto){
        try{
            Application application = mongoService.getApplication(id);
            application.setUrlAplicacionWeb(applicationDto.getUrlApplicationWeb());
            application.setNombreAplicacion(applicationDto.getApplicationName());
            mongoService.saveApplication(application);
            return true;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
