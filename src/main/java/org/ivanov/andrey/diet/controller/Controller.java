package org.ivanov.andrey.diet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // Указывает, что это REST-контроллер
@RequestMapping("/calories") // Базовый URL для запросов
public class Controller {

    @GetMapping("/hello") // Обрабатывает GET-запрос на /api/hello
    public String sayHello(@RequestParam String name) {
        return "Привет, %s  Spring Boot!".formatted(name);
    }
}