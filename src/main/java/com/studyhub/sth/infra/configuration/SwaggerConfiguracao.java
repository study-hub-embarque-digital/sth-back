package com.studyhub.sth.infra.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(
        contact = @Contact(name = "STH - StudyHub"),
        title = "STH Api",
        version = "1.0.0"))
@Configuration
public class SwaggerConfiguracao {
}