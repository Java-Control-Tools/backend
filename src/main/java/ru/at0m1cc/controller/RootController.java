package ru.at0m1cc.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.at0m1cc.log.Logger;

import java.io.IOException;

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
