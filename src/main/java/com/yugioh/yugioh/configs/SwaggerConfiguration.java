package com.yugioh.yugioh.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// @Profile("docker")
//@Configuration
//@EnableSwagger2
public class SwaggerConfiguration {
	private final Logger log = LoggerFactory.getLogger(SwaggerConfiguration.class);

	@Bean
	public Docket swaggerSpringfoxDocket() {
		log.debug("Starting Swagger");
		StopWatch watch = new StopWatch();
		watch.start();
		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().pathMapping("/")
				.genericModelSubstitutes(ResponseEntity.class)
				.ignoredParameterTypes(Pageable.class)
				.ignoredParameterTypes(java.sql.Date.class)
				.directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
				.directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
				.directModelSubstitute(java.time.LocalDateTime.class, Date.class)
				.useDefaultResponseMessages(false)
				.apiInfo(apiInfo())
				.securitySchemes(securitySchemes());
		watch.stop();
		log.debug("Started Swagger in {} ms", watch.getTotalTimeMillis());
		return docket;
	}

	private List<SecurityScheme> securitySchemes() {
		return Collections.singletonList(new ApiKey("Bearer", "Authorization", "header"));
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("Application", "REST API", "1.1.0", "Terms of service",
				new Contact("Kristijan Kostovski", "", "kristijan.kostovski@students.finki.ukim.mk"),
				"", "", new ArrayList<>());
	}
}
