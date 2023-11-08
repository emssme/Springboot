package com.shop.configuration;

import com.shop.service.BookService;
import com.shop.service.BookServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebConfig {
    @Bean
    public BookService bookService(){
        return new BookServiceImpl();
    }
}
