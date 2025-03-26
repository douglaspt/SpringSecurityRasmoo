package com.client.api.rasmooplus.configuration;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Minha API com Keycloack")
                        .version("1.0")
                        .description("Documentação da API usando Swagger no Spring Boot 3.2.2"));
    }
}
