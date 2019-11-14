package com.autoconfigrue.springbootautoconfigrue.configuration;


import com.autoconfigrue.springbootautoconfigrue.annotation.EnableHelloWorld;
import org.springframework.context.annotation.Configuration;

@Configuration // Spring 模式注解装配
@EnableHelloWorld // Spring @Enable 模块装配
//@ConditionalOnSystemProperty(name = "user.name", value = "Mercy") // 条件装配
public class HelloWorldAutoConfiguration {
}
