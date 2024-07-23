package ru.at0m1cc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * Контроллер для возвращения index.html
 * @author at0m1cc
 * @version 1.1
 * */
@Controller
public class RootController {
    /**
     * Возвращение index.html по корню
     * */
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
