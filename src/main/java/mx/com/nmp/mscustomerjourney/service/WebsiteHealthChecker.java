package mx.com.nmp.mscustomerjourney.service;

import mx.com.nmp.mscustomerjourney.model.constant.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
@Service
public class WebsiteHealthChecker {
    private static final Logger log =  LogManager.getLogger(WebsiteHealthChecker.class);
    @Async
    @Scheduled(cron = "${web.cron}")
    public void websiteStatus(){
        try{
            URL urlWebsite = new URL(Constants.WEB_MONTE);
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlWebsite.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode()==200){
                log.info("EL SITIO ESTA ACTIVO");
            }
            else {
                log.info("NO SE PUDO ESTABLECER LA CONEXION CON EL WEBSITE"+ httpURLConnection.getResponseCode());
            }
        }catch(Exception e){
            log.info("NO SE PUDO ESTABLECER LA CONEXION CON EL WEBSITE");
        }
    }

}
