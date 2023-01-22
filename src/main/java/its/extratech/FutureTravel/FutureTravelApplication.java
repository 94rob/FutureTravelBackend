package its.extratech.FutureTravel;

import com.fasterxml.jackson.databind.ObjectMapper;
import its.extratech.FutureTravel.servicies.implementations.RecordServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

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
	public void doSomethingAfterStartup() throws IOException {
		SetUp setUp = this.setUp();
		setUp.fetchIstatApi();
		setUp.updatePredictionModels();
	}

}
