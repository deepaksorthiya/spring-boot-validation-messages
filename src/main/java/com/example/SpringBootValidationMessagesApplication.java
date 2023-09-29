package com.example;

import jakarta.validation.Validator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

@SpringBootApplication
public class SpringBootValidationMessagesApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootValidationMessagesApplication.class, args);
        Map<String, Validator> beans = applicationContext.getBeansOfType(Validator.class);
        System.out.println(beans);
    }

}
