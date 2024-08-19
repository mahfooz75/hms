package com.selftechlearner.mhr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class MhrApplication {

	public static void main(String[] args) {
		SpringApplication.run(MhrApplication.class, args);
	}

}
