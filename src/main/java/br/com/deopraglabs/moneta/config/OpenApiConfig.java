package br.com.deopraglabs.moneta.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Moneta")
                        .version("v1")
                        .description("API for Moneta")
                        .termsOfService("")
                        .license(new License()
                                .name("MIT license")
                                .url("https://github.com/Deoprag/moneto/blob/main/LICENSE")
                        )
                );
    }
}
