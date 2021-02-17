package com.tech.springboot.Assignment;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
@OpenAPIDefinition
public class SwaggerConfiguration {

	  private final String moduleName;
	  private final String apiVersion;

	  public SwaggerConfiguration(
	      @Value("${module-name}") String moduleName,
	      @Value("${api-version}") String apiVersion) {
	    this.moduleName = moduleName;
	    this.apiVersion = apiVersion;
	  }
	  
	  @Bean
	  public OpenAPI customOpenAPI() {
	    final String securitySchemeName = "bearerAuth";
	    final String apiTitle = String.format("%s API", StringUtils.capitalize(moduleName));
	    return new OpenAPI()
	        .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
	        .components(
	            new Components()
	                .addSecuritySchemes(securitySchemeName,
	                    new SecurityScheme()
	                        .name(securitySchemeName)
	                        .type(SecurityScheme.Type.APIKEY)
	                		.name("Authorization")
	                		.scheme("")
	                		.in(In.HEADER)
	                		)
	        )


			.addServersItem(new Server()
							.url("http://localhost:8080")
							.description("LOCAL  APPLICATION"))
			.info(new Info()
					.title(apiTitle)
					.version(apiVersion));
	  }
}

