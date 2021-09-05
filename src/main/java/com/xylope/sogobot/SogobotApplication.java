package com.xylope.sogobot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SogobotApplication {

    public static void main(String[] args) {
        SpringApplication.run(SogobotApplication.class, args);
    }

}
