package uz.pdp.userservice.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securityScheme = "Bearer Token";
        final String apiTitle = String.format(StringUtils.capitalize("User Service API"));
        final String[] developer = {"Mirzayev Bekzod"};
        final String apiDescription = String.format("""
                        Ushbu REST API %s uchun yaratilgan.\s
                        
                        Dasturchi: %s
                        """,
                apiTitle,
                Arrays.toString(developer)
        );
        return new OpenAPI()
                .addServersItem(new Server().url("/user-service-api").description("Root API server"))
                .components(new Components()
                        .addSecuritySchemes(securityScheme,
                                new SecurityScheme()
                                        .description("JSON Web Token")
                                        .in(SecurityScheme.In.HEADER)
                                        .name("Authorization")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("Bearer")
                                        .bearerFormat("JWT")
                        )
                )
                .info(new Info()
                        .title(apiTitle)
                        .version("2.0")
                        .description(apiDescription)
                )
                .addSecurityItem(new SecurityRequirement().addList(securityScheme)
                );
    }
}
