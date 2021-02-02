import mx.com.nmp.mscustomerjourney.CustomerjourneyApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)

public class CustomerJourneyApplicationTest {

    @InjectMocks

    private CustomerjourneyApplication cjApplication;

    @Test
    public void main(){

        String[] args = new String[0];
        cjApplication.main(args);

    }
}
