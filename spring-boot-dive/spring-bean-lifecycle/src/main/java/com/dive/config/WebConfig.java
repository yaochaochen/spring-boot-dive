package com.dive.config;

import com.dive.domain.Bird;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class WebConfig {

//     @Scope("prototype")
//     @Bean(initMethod = "init", destroyMethod = "destory")
//     public User user() {
//         return new User();
//     }
     @Bean
     public Bird bird() {
         return new Bird();
     }

     @Bean
    public MyBeanPostProcessor myBeanPostProcessor() {
         return new MyBeanPostProcessor();
     }
}
