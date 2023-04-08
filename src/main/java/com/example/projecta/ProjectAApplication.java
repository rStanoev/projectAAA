package com.example.projecta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjectAApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectAApplication.class, args);
    }

}
