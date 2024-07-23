package ru.at0m1cc.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.at0m1cc.db.UserPC;
import ru.at0m1cc.service.JavaControlService;

import java.util.List;

/**
 *  API для работы с пользователями
 * @author at0m1cc
 * @version 1.1
 * */
@RestController
@RequestMapping("/api/jc")
public class JavaControlController {
    /**
     * Сервис работы с бд
     * */
    private final JavaControlService javaControlService;
    /**
     * Конструктор с аннотацией Autowired для автоматического внедрения зависимостей
     * */
    @Autowired
    public JavaControlController(JavaControlService javaControlService) {
        this.javaControlService = javaControlService;
    }
    /**
     * API для отображения пользователей
     * */
    @GetMapping("/showUsers")
    @CrossOrigin("*")
    public List<UserPC> showUsers(HttpSession session) {
        if(session.getAttribute("login") == null) {//!!!!!!!!!!!
            return javaControlService.showUsers();
        }
        return null;
    }
    /**
     * API для обработки команд
     * */
    @PostMapping("/controlUser")
    @CrossOrigin("*")
    public String control(HttpSession session, @RequestParam("ipAddress") String ipAddress, @RequestParam("port") String port, @RequestParam("command") String command) {
        if(session.getAttribute("login") == null) { //!!!!!!
            if(javaControlService.sendCommandToUserPC(ipAddress, port, command)) {
                return "{\"status\":\"OK\"}";
            }
            else {
                return "{\"status\":\"ERROR\"}";
            }
        }
        return null;
    }
    /**
     * API для добавления ПК пользователя в бд
     * */
    @PostMapping("/addUserPC")
    @CrossOrigin("*")
    public String addUser(HttpSession session, @RequestParam("ipAddress") String ipAddress, @RequestParam("port") String port){
        if(port.isEmpty()){
            port = "5556";
        }
        if(session.getAttribute("login") == null) {//!!!!!!
            if(javaControlService.addUser(ipAddress, port)) {
                return "{\"status\":\"OK\"}";
            }
            else {
                return "{\"status\":\"ERROR\"}";
            }
        }
        return null;
    }

    @PostMapping("/deleteUser")
    @CrossOrigin("*")
    public String deleteUser(HttpSession session, @RequestParam("ipAddress") String ipAddress, @RequestParam("port") String port){
        if(session.getAttribute("login") == null) {//!!!!
            if(javaControlService.deleteUser(ipAddress, port)) {
                return "{\"status\":\"OK\"}";
            }
            else {
                return "{\"status\":\"ERROR\"}";
            }
        }
        return null;
    }
}
