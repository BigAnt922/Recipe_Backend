package com.dhirajb7.recipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@OpenAPIDefinition(info = @Info(title = "Recipes API", version = "1.0", description = "API used for Recipes DB", contact = @Contact(name = "Dhiraj B", url = "https://portfolio-dhirajb7.vercel.app/", email = "dhiraj.basavaraju@gamil.com")))
public class RecipeBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeBackendApplication.class, args);
	}

}
