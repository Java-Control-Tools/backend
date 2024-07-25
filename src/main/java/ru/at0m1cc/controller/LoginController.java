package ru.at0m1cc.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.at0m1cc.dto.StatusCode;
import ru.at0m1cc.dto.StatusDTO;
import ru.at0m1cc.log.Logger;
import ru.at0m1cc.repository.UserPCRepository;
import ru.at0m1cc.service.LoginService;

import java.util.Date;

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

    private final Logger logger;
    /**
     * Конструктор с аннотацией Autowired для автоматического внедрения зависимостей
     * */
    @Autowired
    public LoginController(LoginService loginService, UserPCRepository userPCRepository, Logger logger) {
        this.loginService = loginService;
        this.logger = logger;
    }
    /**
     * API для проверки атрибута сессии, в случае когда атрибута сессии нет, выполняется проверка пароля
     * */
    @CrossOrigin(origins = "*")
    @PostMapping("/check")
    public ResponseEntity<StatusDTO> login(@RequestParam("password") String password, @RequestHeader(value = "User-Agent") String userAgent, HttpSession session) {
        if(session.getAttribute("login") == null) {
            if(loginService.login(password)) {
                session.setAttribute("login", "OK");
                logger.writeLoginLog(userAgent);
                return ResponseEntity.ok(new StatusDTO(StatusCode.OK));
            }
            else {
                return ResponseEntity.status(401).body(new StatusDTO(StatusCode.ERROR));
            }
        }
        return ResponseEntity.ok(new StatusDTO(StatusCode.OK));
    }
    /**
     * API для выхода из системы
     * */
    @CrossOrigin(origins = "*")
    @PostMapping("/logout")
    public void logout(HttpSession session) {
        session.removeAttribute("login");
    }

    @PostMapping("/changePassword")
    @CrossOrigin("*")
    public ResponseEntity<StatusDTO> changePassword(@RequestHeader(value = "User-Agent") String userAgent,@RequestParam("newPassword") String newPassword, @RequestParam("password") String password, HttpSession session) {
        if(session.getAttribute("login") != null) {
            if(loginService.login(password)) {
                loginService.changePassword(password, newPassword);
                logger.writeChangePasswordLog(userAgent);
                return ResponseEntity.ok().body(new StatusDTO(StatusCode.OK));
            }
        }
        return ResponseEntity.badRequest().body(new StatusDTO(StatusCode.ERROR));
    }
}
