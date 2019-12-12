package com.dive.springservlet.controller;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@RestController
@EnableScheduling
public class HelloWorldAsyncController {

    private final BlockingQueue<DeferredResult<String>> queue = new ArrayBlockingQueue<>(5);

    private final Random random = new Random();



    @GetMapping("/hello")
    public DeferredResult<String> helloWorld() {
        DeferredResult<String> result = new DeferredResult<>();
        result.setResult("Hello World");
        result.onCompletion(()-> println("执行结束"));
        return result;
    }
    @GetMapping("/completion-stage")
    public CompletionStage<String> completionStage() {
        final long startTime = System.currentTimeMillis();
        println("Hello, World");

        return CompletableFuture.supplyAsync(()->{
           long costTime = System.currentTimeMillis() - startTime;
           println("执行结果, 耗时" + costTime + "ms.");
           return "Hello, World";
        });
    }

    private static  void println(Object object) {
        String threadName = Thread.currentThread().getName();
        System.out.println("当前线程【" + threadName + "】" + object);
    }
}
