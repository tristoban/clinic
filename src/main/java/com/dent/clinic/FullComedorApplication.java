package com.dent.clinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class FullComedorApplication {

	public static void main(String[] args) {
		/*
			Hola profe!!
			Una vez ejecutado el proyecto debe ir al link:
			http://localhost:8080/login
			y poner estas credenciales:
			admin@fullcomedor.com
			admin123

			(le ahorro el ir hasta data loader jajaja)

			Muchas gracias por el tremendo bimestre de aprendizaje!

			Un abrazo!
		 */
		SpringApplication.run(FullComedorApplication.class, args);
	}

}
