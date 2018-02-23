package com.planetofthetapes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.planetofthetapes.controller.MasterService;

@SpringBootApplication
public class PlanetOfTheTapesApplication extends MasterService{
	
	@Bean
	public MasterService masterService(){
		return new MasterService();
	}

	public static void main(String[] args) {
		SpringApplication.run(PlanetOfTheTapesApplication.class, args);
	}
}
