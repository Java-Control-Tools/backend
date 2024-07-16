package ru.at0m1cc.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.at0m1cc.db.UserPC;
import ru.at0m1cc.repository.UserPCRepository;
import ru.at0m1cc.service.LoginService;

/**
 *  API для работы с авторизацией
 * @author at0m1cc
 * @version 1.1
 * */
@RestController
@RequestMapping("/api/login")
public class LoginController {
    /**
     * Сервис с логикой проверки пароля
     * */
    private final LoginService loginService;
    private final UserPCRepository userPCRepository;
    /**
     * Конструктор с аннотацией Autowired для автоматического внедрения зависимостей
     * */
    @Autowired
    public LoginController(LoginService loginService, UserPCRepository userPCRepository) {
        this.loginService = loginService;
        this.userPCRepository = userPCRepository;
    }
    /**
     * API для проверки атрибута сессии, в случае когда атрибута сессии нет, выполняется проверка пароля
     * */
    @CrossOrigin(origins = "*")
    @PostMapping("/check")
    public String login(@RequestParam("password") String password, HttpSession session) {
        if(session.getAttribute("login") == null) {
            if(loginService.login(password)) {
                session.setAttribute("login", "OK");
                userPCRepository.save(new UserPC("1","1","1"));
                return "{\"status\":\"OK\"}";
            }
            else {
                return "{\"status\":\"ERROR\"}";
            }
        }
        else {
            return "{\"status\":\"OK\"}";
        }
    }
    /**
     * API для выхода из системы
     * */
    @CrossOrigin(origins = "*")
    @PostMapping("/logout")
    public void logout(HttpSession session) {
        session.removeAttribute("login");
    }
}
