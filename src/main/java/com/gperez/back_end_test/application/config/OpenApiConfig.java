package com.gperez.back_end_test.application.config;


import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "API Productos Similares",
        description = "API para consultar productos similares",
        version = "v1",
        contact = @Contact(
            name = "Gonzalo N. Pérez",
            email = "gonzaloperez@fakeEmail.com"
        )
    )   
)
public class OpenApiConfig {
    
}
