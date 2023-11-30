package com.shop2.ctrl;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class HelloCtrl {
    @GetMapping("/hello")
    public String hello1(Model model){
        String hello = "Hello Spring Boot JPA";
        model.addAttribute("msg", hello);
        log.info(hello);
        return "hello1";
    }
}