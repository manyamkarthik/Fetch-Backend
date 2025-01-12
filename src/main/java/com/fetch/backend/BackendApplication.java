package com.fetch.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Fetch Assesment",
				contact = @Contact(
						name = "Karthik Manyam",
						email = "harikarthikmanyam@gmail.com",
						url = "https://manyam-karthik.netlify.app/"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  " REST API Documentation with OPENAPI",
				url = "http://localhost:8080/swagger-ui.html"
		)
)
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
