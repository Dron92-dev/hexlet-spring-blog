package io.hexlet.springblog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Привет! Это мой первый Spring Boot проект!";
    }

    @GetMapping("/about")
    public String about() {
        return "This is simple Spring blog!";
    }
}
