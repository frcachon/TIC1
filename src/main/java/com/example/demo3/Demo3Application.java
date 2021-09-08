package com.example.demo3;

import com.example.demo3.entities.Cliente;
import com.example.demo3.persistence.ClienteRepository;
import com.example.demo3.ui.ApplicationJFX;
import javafx.application.Application;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Demo3Application {

		private static ConfigurableApplicationContext context;

		public static void main(String[] args) {
			Demo3Application.context = SpringApplication.run(Demo3Application.class);
			Application.launch(ApplicationJFX.class, args);
		}

		public static ConfigurableApplicationContext getContext() {
			return context;
		}



/*
	@Bean
	public CommandLineRunner run(ClienteRepository repository) {
		return (args) -> {
			insertFourClientes(repository);
			System.out.println(repository.findAll());

			System.out.println(repository.findClienteByNameContaining(" "));
		};
	}
*/
	/*private void insertFourClientes(ClienteRepository repository) {
		repository.save(new Cliente("Juan", 11111111L, "juan@gmail.com")); //esto no va porque los vamos a ingresar por la interfaz de usuario
		repository.save(new Cliente("Luis", 22222222L,"luis@gmail.com"));
		repository.save(new Cliente("Jose", 33333333L, "jose@gmail.com"));
		repository.save(new Cliente("Pedro", 44444444L, "pedro@gmail.com"));
	}
*/
}






