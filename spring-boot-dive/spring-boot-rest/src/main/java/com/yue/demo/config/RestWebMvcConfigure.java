package com.yue.demo.config;

import com.yue.demo.http.conveter.properties.PropertiesHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class RestWebMvcConfigure implements WebMvcConfigurer {



    @Override
     public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new PropertiesHttpMessageConverter());
        //将排序第一位的优先级
       // converters.set(0, new PropertiesHttpMessageConverter());


    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("").allowedOrigins("*");
    }


    }
