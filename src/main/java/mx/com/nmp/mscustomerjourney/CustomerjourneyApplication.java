package mx.com.nmp.mscustomerjourney;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableScheduling
@SpringBootApplication
public class CustomerjourneyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerjourneyApplication.class, args);
	}

}
