package com.dive.web.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 视图引擎
 */
@SpringBootApplication(scanBasePackages = "com.dive.web")
public class SpringBootViewBootstrap {
    public static void main(String[] args) {

        SpringApplication.run(SpringBootViewBootstrap.class, args);
    }
}
