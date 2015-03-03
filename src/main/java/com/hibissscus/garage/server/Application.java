package com.hibissscus.garage.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

/**
 * Spring Boot entry point.
 *
 * @author hibissscus
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.hibissscus.garage.server")
public class Application extends SpringBootServletInitializer {


    final static Logger LOGGER = LoggerFactory.getLogger(Application.class);

    /**
     * The Env.
     */
    @Autowired
    Environment env;


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    /**
     * Entry point.
     *
     * @param args main args.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
