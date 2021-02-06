package com.example.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Enable swagger 2 can be put on a Configuration class
 */
@SpringBootApplication
@EnableSwagger2
public class SwaggerexampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwaggerexampleApplication.class, args);
	}

	/**
	 * Configuration class for the Swagger UI look on /swagger-ui.html
	 * @return Docket
	 */
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				// Displays specification only for the selected routes
				.paths(PathSelectors.ant("/api/*"))
				.build()
				.apiInfo(configureApiInfo());
	}

	private ApiInfo configureApiInfo() {
		return new ApiInfo(
				"This is the title",
				"This is the description",
				"The best version",
				"Terms of service URL ofcourse",
				new Contact("Contact name", "Contact URL", "Contact email. Please dont."),
				"I gues I have a license",
				"This is the license URL",
				Collections.emptyList()
		);
	}

}
