package com.autoconfigrue.springbootautoconfigrue.bootstrap;

import com.autoconfigrue.springbootautoconfigrue.repository.MyFirstLevelRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.autoconfigrue.springbootautoconfigrue.repository")
public class RepositoryBootStrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = new SpringApplicationBuilder(RepositoryBootStrap.class)
                .web(WebApplicationType.NONE).run(args);

        MyFirstLevelRepository myFirstLevelRepository =
                configurableApplicationContext.getBean("myFirstLevelRepository", MyFirstLevelRepository.class);
        System.out.printf( "myFirstLevelRepository Bean " + myFirstLevelRepository);
        configurableApplicationContext.close();
    }
}
