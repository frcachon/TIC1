package com.example.demo3;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Demo3Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo3Application.class, args);
	}

	@Bean
	public CommandLineRunner run(ClienteRepository repository) {
		return (args) -> {
			insertCliente(repository);
			System.out.println(repository.findAll());
		};
	}

	private void insertCliente(ClienteRepository repository) {
		repository.save(new Cliente("Juan", 11111111L, "juan@gmail.com"));
	}

}

/*	@Bean
	public CommandLineRunner run(ClienteRepository repository) {
		return (args) -> {
			ClienteMgr clienteMgr = new ClienteMgr();
			clienteMgr.addClient(11111111L,"Francisco", "emailfrancisco");
			System.out.println(repository.findAll());
			System.out.println(repository.findClienteByDocument(52359065L));
		};
	}*/