package com.example.bank_application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
//@EnableSwagger2WebMvc
//@EnableSwagger2
public class SwaggerConfig {

//    @Bean
//    public GroupedOpenApi infoServiceApi() {
//        return GroupedOpenApi.builder()
//                .group("bank-application")
//                .pathsToMatch("/accounts/**", "/clients/**", "/managers/**")
//                .build();
//    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

//    @Bean
//    public OpenAPI customOpenApi(@Value("${app.description}") String appDescription,
//                                 @Value("${app.version}") String appVersion,
//                                 @Value("${app.server.url}") String serverUrl,
//                                 @Value("${app.server.stage}") String stage) {
//        return new OpenAPI()
//                .info(new Info().title("Info service API")
//                        .version(appVersion)
//                        .description(appDescription))
//                .servers(List.of(new Server()
//                        .url(serverUrl)
//                        .description(stage)));
//    }
}
