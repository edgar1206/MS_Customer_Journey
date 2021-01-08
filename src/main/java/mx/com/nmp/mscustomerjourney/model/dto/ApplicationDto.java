package mx.com.nmp.mscustomerjourney.model.dto;

import java.io.Serializable;

public class ApplicationDto implements Serializable {

    private String urlApplicationWeb;
    private String applicationName;

    public String getUrlApplicationWeb() {
        return urlApplicationWeb;
    }

    public void setUrlApplicationWeb(String urlApplicationWeb) {
        this.urlApplicationWeb = urlApplicationWeb;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

}
