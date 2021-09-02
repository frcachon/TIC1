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
			insertFourClientes(repository);
			System.out.println(repository.findAll());

			System.out.println(repository.findClienteByNameContaining(" "));
		};
	}

	private void insertFourClientes(ClienteRepository repository) {
		repository.save(new Cliente("Juan", 11111111L, "juan@gmail.com"));
		repository.save(new Cliente("Luis", 22222222L,"luis@gmail.com"));
		repository.save(new Cliente("Jose", 33333333L, "jose@gmail.com"));
		repository.save(new Cliente("Pedro", 44444444L, "pedro@gmail.com"));
	}

}
