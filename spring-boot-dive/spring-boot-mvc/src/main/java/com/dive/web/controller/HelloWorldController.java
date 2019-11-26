package com.dive.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class  HelloWorldController {
    @RequestMapping("")
    public String index(Model model) {
      //  model.addAttribute("acceptLanguage",acceptLanguage);
        model.addAttribute("message","Hello,World");
        return "index";
    }
}
