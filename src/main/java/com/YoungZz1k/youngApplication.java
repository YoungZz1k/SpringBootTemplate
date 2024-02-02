package com.YoungZz1k;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.YoungZz1k")
public class youngApplication {
    public static void main(String[] args) {
        SpringApplication.run(youngApplication.class,args);
    }
}
