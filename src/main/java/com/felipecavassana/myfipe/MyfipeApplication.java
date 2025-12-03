package com.felipecavassana.myfipe;

import com.felipecavassana.myfipe.service.FipeApiService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyfipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MyfipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		FipeApiService fipeApiService = new FipeApiService();
		fipeApiService.showMenu();
	}
}
