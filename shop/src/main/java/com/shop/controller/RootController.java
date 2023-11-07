package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
    @GetMapping("/")
    public String home(){
        return "index";
    }
    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("msg", "메시지");
        return "hello";
    }
}
