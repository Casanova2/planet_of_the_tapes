package com.planetofthetapes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.planetofthetapes.controller.MasterService;
import com.planetofthetapes.controller.storage.StorageProperties;
import com.planetofthetapes.controller.storage.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class PlanetOfTheTapesApplication extends MasterService{
	
	@Bean
	public MasterService masterService(){
		return new MasterService();
	}
	
	@Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }

	public static void main(String[] args) {
		SpringApplication.run(PlanetOfTheTapesApplication.class, args);
	}
}
