package com.yue.demo.controller;

import com.yue.demo.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
public class UserController {

    @PostMapping(value = "/echo/user", consumes = "text/properties;charset=utf-8")
    public String  getUser(@RequestBody Properties user) {
        return  "Success";
    }
}
