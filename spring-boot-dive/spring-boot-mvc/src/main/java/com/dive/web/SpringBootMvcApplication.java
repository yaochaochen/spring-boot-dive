package com.dive.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.dive.web")
public class SpringBootMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMvcApplication.class, args);
    }

}
