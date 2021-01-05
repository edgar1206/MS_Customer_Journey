package mx.com.nmp.mscustomerjourney.task;

import mx.com.nmp.mscustomerjourney.model.catalogo.Errores;
import mx.com.nmp.mscustomerjourney.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Component
public class Monitoreo {
    @Autowired
    private MongoService mongoService;

    private List<String> urlList;

    private static final Logger LOGGER = Logger.getLogger(Monitoreo.class.getName());

    public Monitoreo(){
        urlList = new ArrayList<>();
        urlList.add("https://dev1775-frontmimontepagos.mybluemix.net/login");
        urlList.add("https://iamdr.montepiedad.com.mx:4444/NMP/oauth/token");
        urlList.add("https://dev1775-mimonte-fase2.mybluemix.net:443/mimonte/v1/tarjetas/cliente");
        urlList.add("https://iamdr.montepiedad.com.mx:4444/NMP/OperacionPrendaria/OperacionesEnLinea/Transaccion.svc/v1/Recibos/Detalle");
        urlList.add("https://417aa3f7200b4e4ab27e35032757cadd.us-east-1.aws.found.io:9243/mm_promociones_dev/_search?size=10");
        urlList.add("https://iamdr.montepiedad.com.mx:4444/NMP/GestionClientes/UsuariosMonte/v1/validarDatos");
        urlList.add("https://iamdr.montepiedad.com.mx:4444/NMP/OperacionPrendaria/Partidas/v1/Cliente");
    }

    //@Scheduled(cron = "${event.cron.time}")
    public void startTask(){
        LOGGER.info("inicia ejecucion de monitoreo " + new Date());
        //urlList = serviciosMongo.getServicios();
        urlList.stream().parallel().forEach(this::monitoreo);
        LOGGER.info("termina ejecucion de monitoreo " + new Date());
    }

    @Async("threadPoolTaskExecutor")
    public void monitoreo(String url){
        try {
            URL siteURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(1000);
            connection.connect();
            int code = connection.getResponseCode();
            LOGGER.info(code + " -> " + url);
            if (code == 404 || code == 408) {
                LOGGER.info("error -> " + code + ": " + url);
            }
        } catch (Exception e) {
            LOGGER.info("error -> " + url);
        }
    }
    @Scheduled(cron = "${carga.cron.time}")
    public void cargaCatalogo(){
        mongoService.cargaCatalogo();
    }

    @Scheduled(cron = "${restablece.cron.time}")
    public void restableceCatalogo(){
        List<Errores> erroresLista= mongoService.getListaErrores();
        for (Errores error: erroresLista) {
            long tiempoMs = new Date().getTime() - error.getUltimaActualizacion().getTime();
            long diferenciaMinutos = tiempoMs / (1000 * 60);
            if(diferenciaMinutos >=5 && !error.getAlertamiento().isEmpty()){
                error.setAlertamiento("");
                error.setUltimaActualizacion(new Date());
                mongoService.saveError(error);
            }
        }
    }
}
