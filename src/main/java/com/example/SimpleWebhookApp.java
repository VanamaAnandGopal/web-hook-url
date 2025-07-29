package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleWebhookApp {
    public static void main(String[] args) {
        SpringApplication.run(SimpleWebhookApp.class, args);
        System.out.println("server is running  http://localhost:8080");
    }
}