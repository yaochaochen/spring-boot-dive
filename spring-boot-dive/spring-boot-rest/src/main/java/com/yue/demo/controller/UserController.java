package com.yue.demo.controller;

import com.yue.demo.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping(value = "/echo/user")
    public User getUser(@RequestBody User user) {
        return  user;
    }
}
