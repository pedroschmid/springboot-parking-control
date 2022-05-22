package com.api.parkingcontrol.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .info(
                    new Info()
                        .title("Parking Control API")
                        .description("Parking Control application")
                        .version("v1.0")
                        .license(
                            new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")
                ))
                .externalDocs(
                    new ExternalDocumentation()
                        .description("Parking Control external documentation")
                        .url("http://localhost:8080/")
                    )
                .components(new Components());
    }

}
