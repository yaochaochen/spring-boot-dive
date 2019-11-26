package com.dive.web.bootstrap.template.engine;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class ThymeleafTemplateEngineBootstrap {

    public static void main(String[] args) throws IOException {
        //构建引擎
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();

        //构建上下文
        Context context = new Context();
        //渲染
        context.setVariable("message", "Hello, World");
        //// 读取内容从 classpath:/templates/thymeleaf/hello-world.html
        ResourceLoader resourceLoader = new DefaultResourceLoader();

        Resource resource = resourceLoader.getResource("/templates/thymeleaf/hello-world.html");
        File templateFile = resource.getFile();
        FileInputStream fileInputStream = new FileInputStream(templateFile);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        IOUtils.copy(fileInputStream, outputStream);
        //关闭流
        fileInputStream.close();
        String content = outputStream.toString("UTF-8");
        String result = templateEngine.process(content, context);
        System.out.println(result);


    }
}
