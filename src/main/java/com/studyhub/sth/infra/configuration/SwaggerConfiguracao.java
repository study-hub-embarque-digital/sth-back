package com.studyhub.sth.infra.configuration;

import com.studyhub.sth.libs.application.FieldUtilities;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.media.*;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@OpenAPIDefinition(info = @Info(
        contact = @Contact(name = "STH - StudyHub"),
        title = "STH Api",
        version = "1.0.0"),
        security = @SecurityRequirement(name = "JWT Token"))
@SecurityScheme(
        name = "JWT Token",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
@Configuration
public class SwaggerConfiguracao {
//    @Bean
//    public OpenApiCustomizer customizeSuccessResponse() {
//        return openApi -> {
//            Components components = openApi.getComponents();
//
//            // Referência ao schema já gerado de AuthResponse
//            Schema<?> authResponseRef = new Schema<>().$ref("#/components/schemas/AuthResponse");
//            Schema<?> successResponseRef = new Schema<>().$ref("#/components/schemas/SuccessResponse");
//authResponseRef.
//            // Construção do schema baseado no SuccessResponse
//            Schema<?> successSchema = new ObjectSchema()
//                    .addAllOfItem(successResponseRef)
//                    .addProperty("data", new Schema())
//                    .name("SuccessAuthResponse");
//
//            // Registra esse novo schema no OpenAPI
//            components.addSchemas("SuccessAuthResponse", successSchema);
//        };
//    }
//
//    private Schema<?> responseSchema() {
//        return new ObjectSchema()
//                .addProperty("data", authResponseRef)
//                .name("SuccessAuthResponse");
//
//    }
//
//    private<T> void registerSchema(T clas) {
//        String schemaName = clas.getClass().getName() + "Schema";
//        List<ClassProperties> classProperties = Arrays.stream(clas.getClass().getDeclaredFields()).map(f -> new ClassProperties(f.getName(), f)).toList();
//    }
//
//    class ClassProperties {
//        public String fieldName;
//
//        public ClassProperties(String fieldName, Field field) {
//            this.fieldName = fieldName;
//            field.getType().getSimpleName();
//        }
//
//        private Schema<> selectSchemaType(Field field) {
//            if (FieldUtilities.isListType(field)) {
//                return new ArraySchema();
//            }
//
//            if (FieldUtilities.isNumericType(field)) {
//                return new StringSchema();
//            }
//
//            if (FieldUtilities.isNumericType(field)) {
//                return new NumberSchema();
//            }
//
//            if (FieldUtilities.isBooleanType(field)) {
//                return new BooleanSchema();
//            }
//
//            if (FieldUtilities.isDateType(field)) {
//                return new DateSchema();
//            }
//
//            if (FieldUtilities.isUUIDType(field)) {
//                return new UUIDSchema();
//            }
//
//            Schema<?> objectSchema = new Schema<>().$ref("#/components/schemas/" + fieldName.);
//        }
//    }
}