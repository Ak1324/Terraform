package com.example.demopoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemopocApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemopocApplication.class, args);
    }
    @GetMapping("/")
    public String home() {
        return "Hello, DevOps!";
    }
}
