package com.quality_air.quality_air_backend.utilities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.ExternalDocumentation;

@Configuration
public class OpenApiConfig {
	
	@Value("${name.api}")
	private String nameApi;
	
	@Value("${description.api}")
	private String descriptionApi;
	
	@Value("${dev.team.url.api}")
	private String devTeamUrl;
	
	@Value("${repo.url.api}")
	private String repoUrl;

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info().title(this.nameApi).version("1.0")
						.description(descriptionApi)
						.contact(new Contact().name("Development Team").url(devTeamUrl)))
				.externalDocs(new ExternalDocumentation().description("GitHub repository")
						.url("repoUrl"));
	}
}