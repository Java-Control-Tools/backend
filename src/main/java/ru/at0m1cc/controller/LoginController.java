package ru.at0m1cc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.at0m1cc.dto.StatusCode;
import ru.at0m1cc.dto.StatusDTO;
import ru.at0m1cc.log.Logger;
import ru.at0m1cc.service.UserService;

/**
 *
 * @author at0m1cc
 * @version 1.1
 * */
@Controller
public class LoginController {

    private final UserService userService;
    private final Logger logger;


    @Autowired
    public LoginController(UserService userService, Logger logger) {
        this.userService = userService;
        this.logger = logger;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/changePassword")
    @CrossOrigin("*")
    public ResponseEntity<StatusDTO> changePassword(@RequestHeader(value = "User-Agent") String userAgent,
                                                    @RequestParam("newPassword") String newPassword,
                                                    @RequestParam("password") String password) {
        logger.writeChangePasswordLog(userAgent);
        String username = SecurityContextHolder.getContext().getAuthentication().getName(); // ПОлучение имени текушего пользователя
        StatusDTO statusDTO = userService.changePassword(username,password,newPassword);
        if (statusDTO.getStatus().equals("OK")) {
            return ResponseEntity.ok(statusDTO);
        }
        return ResponseEntity.status(401).body(statusDTO);
    }
}
