package com.dive.springservlet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class HelloWorldAsnyController {

    @GetMapping("/hello")
    public DeferredResult<String> helloWorld() {
        DeferredResult<String> result = new DeferredResult();
        result.setResult("Hello World");
        result.onCompletion(()-> System.out.println("执行结束"));
        return result;
    }

    private static  void printla(Object object) {
        String threadName = Thread.currentThread().getName();
        System.out.println("当前线程【" + threadName + "】");
    }
}
