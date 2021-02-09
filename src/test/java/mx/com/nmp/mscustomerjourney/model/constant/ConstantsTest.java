package mx.com.nmp.mscustomerjourney.model.constant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
 class ConstantsTest {
    @InjectMocks
    private Constants constants;

    @Test
    void getMS_EVENTOS_URL() {
        constants.getMS_EVENTOS_URL();
    }

    @Test
    void getREFRESH_ALERT() {
        constants.getREFRESH_ALERT();
    }

    @Test
    void getTIEMPO_AUMENTA_ALERTAMIENTO() {
        constants.getTIEMPO_AUMENTA_ALERTAMIENTO();
    }

    @Test
    void getREFRESH_APPLICACION() {
        constants.getREFRESH_APPLICACION();
    }

    @Test
    void getNEW_RELIC_URL() {
        constants.getNEW_RELIC_URL();
    }

    @Test
    void getRESOLUTION_TOWER() {
        constants.getRESOLUTION_TOWER();
    }

    @Test
    void getEVENT_TYPE() {
        constants.getEVENT_TYPE();
    }

    @Test
     void getAPPLICATION_NAME() {
        constants.getAPPLICATION_NAME();
    }

    @Test
     void getAPPLICATION_URL() {
        constants.getAPPLICATION_URL();
    }
}