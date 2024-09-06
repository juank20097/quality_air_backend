package com.quality_air.quality_air_backend.utilities;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.ExternalDocumentation;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Quality Air API").version("1.0")
						.description("Documentación de la API para el proyecto Quality Air")
						.contact(new Contact().name("Equipo de Desarrollo").url("https://github.com/juank20097")))
				.externalDocs(new ExternalDocumentation().description("Repositorio de GitHub")
						.url("https://github.com/juank20097/quality_air_backend/tree/proj_backend"));
	}
}