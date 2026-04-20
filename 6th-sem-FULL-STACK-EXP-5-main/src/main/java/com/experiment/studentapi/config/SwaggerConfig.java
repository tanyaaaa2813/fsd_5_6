package com.experiment.studentapi.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.*;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Student Management REST API")
                .version("1.0.0")
                .description("""
                    ## Spring Boot REST API - Layered Architecture Experiment
                    
                    This API demonstrates:
                    - **Layered Architecture**: Controller → Service → Repository
                    - **Full CRUD**: Create, Read, Update, Delete operations
                    - **Validation**: Input validation with `@Valid` and custom constraints
                    - **Exception Handling**: Global handler with proper HTTP status codes
                    - **In-Memory DB**: H2 database (data resets on restart)
                    
                    ### HTTP Status Codes Used
                    | Code | Meaning |
                    |------|---------|
                    | 200  | OK - Successful GET / PUT |
                    | 201  | Created - Successful POST |
                    | 204  | No Content - Successful DELETE |
                    | 400  | Bad Request - Validation failed |
                    | 404  | Not Found - Student does not exist |
                    | 409  | Conflict - Duplicate email |
                    | 500  | Internal Server Error |
                    """)
                .contact(new Contact()
                    .name("Student API Team")
                    .email("dev@experiment.com"))
                .license(new License()
                    .name("MIT License")))
            .servers(List.of(
                new Server().url("http://localhost:8080").description("Local Development")));
    }
}
