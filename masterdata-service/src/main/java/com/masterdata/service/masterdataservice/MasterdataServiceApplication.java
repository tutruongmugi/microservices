package com.masterdata.service.masterdataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class MasterdataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MasterdataServiceApplication.class, args);
	}

}
