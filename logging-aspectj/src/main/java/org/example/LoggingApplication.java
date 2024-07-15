package org.example;

import org.aspectj.lang.Aspects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LoggingApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingApplication.class);

    private Service service;

    @Autowired
    public LoggingApplication(Service service) {
        this.service = service;
    }

    @Bean
    public LoggingAspect loggingAspect() {
        // This will barf at runtime if the weaver isn't working (probably a
        // good thing)
        return Aspects.aspectOf(LoggingAspect.class);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(LoggingApplication.class, args);
        LoggingApplication loggingApplication = applicationContext.getBean(LoggingApplication.class);
        loggingApplication.start();
    }

    private void start() {
        service.doSomething();
        LOGGER.info(service.doSomethingElse());
    }
}
