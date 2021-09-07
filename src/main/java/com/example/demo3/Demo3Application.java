package com.example.demo3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Demo3Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo3Application.class, args);
	}

	@Autowired
	private ClienteMgr clienteMgr;

	@Bean
	public CommandLineRunner run(ClienteRepository repository) {
		return (args) -> {
			clienteMgr.addClient(52353543L,"Francisco", "emailfrancisco");
			System.out.println(repository.findAll());
		};
	}

}