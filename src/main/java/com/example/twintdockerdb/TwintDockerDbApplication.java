package com.example.twintdockerdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TwintDockerDbApplication {
    public static int globalCounter = 0;

    public static void main(String[] args) {
        SpringApplication.run(TwintDockerDbApplication.class, args);
    }

}
