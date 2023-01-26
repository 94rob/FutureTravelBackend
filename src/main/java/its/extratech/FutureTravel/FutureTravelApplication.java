package its.extratech.FutureTravel;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import java.io.IOException;

@SpringBootApplication
public class FutureTravelApplication {

	@Bean
	public SetUp setUp(){
		return new SetUp();
	}

	@Bean
	public ObjectMapper objectMapper(){
		return new ObjectMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(FutureTravelApplication.class, args);
	}



	@EventListener(ApplicationReadyEvent.class)
	public void setUpData(){
		SetUp setUp = this.setUp();

		try{
			boolean areDataUpdated = setUp.updateDataFromIstat();
			if(areDataUpdated) setUp.updatePredictionModels();

		} catch (IOException ex){
			ex.printStackTrace();
			System.out.println("Error occurred");
		}
	}




}
