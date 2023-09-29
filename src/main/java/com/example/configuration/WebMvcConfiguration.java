package com.example.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
@AllArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {

    ObjectMapper objectMapper;
    MessageSource messageSource;

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {

        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        Hibernate5JakartaModule hibernate5Module = new Hibernate5JakartaModule();
        objectMapper.registerModule(hibernate5Module);
        messageConverter.setObjectMapper(objectMapper);
        return messageConverter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(mappingJackson2HttpMessageConverter());
    }

    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
        factory.setValidationMessageSource(messageSource);
        return factory;
    }

}
