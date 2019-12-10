package com.autoconfigrue.springbootautoconfigrue.configuration;

import org.springframework.context.annotation.Bean;

public class HelloWorldConfiguration {

    @Bean
    public String helloWorld() {
        return "helloWorld2019";
    }

}
