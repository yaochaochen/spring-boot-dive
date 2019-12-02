package com.security.dive.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foo")
@Slf4j
public class FooController {

    @GetMapping("/test")
    public String test() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("current authentication: 【 {} 】", authentication);
        return "success";
    }

    @GetMapping("/bar")
    public String bar() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("current authentication: 【 {} 】", authentication);
        return "bar";
    }


}
