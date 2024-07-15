package ru.at0m1cc.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.at0m1cc.service.LoginService;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    public LoginService loginService;
    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/check")
    public String login(@RequestParam("password") String password, HttpSession session) {
        if(session.getAttribute("login") == null) {
            if(loginService.login(password)) {
                session.setAttribute("login", "OK");
                return "{\"status\":\"OK\",\"session\" : \"" + session.getId() + "\"}";
            }
            else {
                return "{\"status\":\"ERROR\",\"session\" : \"" + session.getId() + "\"}";
            }
        }
        else {
            return "{\"status\":\"OK\"}";
        }
    }
}
