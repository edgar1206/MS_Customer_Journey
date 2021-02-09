package mx.com.nmp.mscustomerjourney.modelError;

public class CognitoError {

    private String retryDelay;
    private String retryable;
    private String code;
    private String requestId;
    private String time;
    private String message;
    private String statusCode;

    public String getCode() {
        return code;
    }

}
