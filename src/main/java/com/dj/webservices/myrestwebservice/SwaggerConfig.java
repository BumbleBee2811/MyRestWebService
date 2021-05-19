package com.dj.webservices.myrestwebservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//Configuration Class
//Enable Swagger
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    //Bean - Docket
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2);
    }
        // Configure Swagger2
        // All the paths
        // All the apis

}
