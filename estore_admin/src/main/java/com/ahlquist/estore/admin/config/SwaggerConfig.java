package com.ahlquist.estore.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import io.swagger.models.Contact;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.ahlquist.estore.admin.controller")).paths(PathSelectors.any())
				.build().apiInfo(apiInfo()).useDefaultResponseMessages(false);
	}

	private ApiInfo apiInfo() {

		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
		apiInfoBuilder.title("Generic RESTful API");
		apiInfoBuilder.description("RESTful API Generation");
		apiInfoBuilder.version("0.0.1-SNAPSHOT");
		// apiInfoBuilder.contact(new Contact("eStore", "https://ahlquist.com",
		// "douglas.ahlquist@gmail.com"));
		// apiInfoBuilder.license("NOT GNU GENERAL PUBLIC LICENSE, Version 3");
		// apiInfoBuilder.licenseUrl("https://www.gnu.org/licenses/gpl-3.0.en.html");
		return apiInfoBuilder.build();
	}

}