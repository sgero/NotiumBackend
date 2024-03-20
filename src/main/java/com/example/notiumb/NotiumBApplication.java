package com.example.notiumb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@SpringBootApplication
public class NotiumBApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotiumBApplication.class, args);
    }


    @Configuration
    @EnableSwagger2
    public static class SwaggerConfig {
    }

}
