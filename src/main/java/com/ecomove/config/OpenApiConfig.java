package com.ecomove.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration professionnelle pour Swagger/OpenAPI.
 *
 * @author Darryl
 * @version 1.0
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI ecomoveOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("EcoMove API - Plateforme de Covoiturage")
                        .description("Documentation complète de l'API REST pour le projet EcoMove. " +
                                "Cette API permet de gérer les utilisateurs, les trajets et les réservations.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Darryl")
                                .email("contact@ecomove.com")
                                .url("https://www.ecomove.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}
