package com.nmurray.todorestdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * The auto generated MAIN class to start up the Spring Boot application
 */
@SpringBootApplication
@EnableMongoRepositories
public class TodoRestDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoRestDemoApplication.class, args);
    }

}
