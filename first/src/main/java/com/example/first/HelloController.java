package com.example.first;

import com.example.first.app.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World";
    }
    @GetMapping("/user")
    public User getUser(){
        return new User(1,"Jyotirmay","jyotirmay@gmail.com");
    }


}
