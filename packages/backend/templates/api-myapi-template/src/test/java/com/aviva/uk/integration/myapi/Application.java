package com.aviva.uk.integration.myapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@SpringBootApplication
@ComponentScan(basePackages = {"com.aviva.uk.integration"})
@EnableAutoConfiguration
public class Application implements WebMvcConfigurer {
    @Autowired
    private DispatcherServlet servlet;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }
}

