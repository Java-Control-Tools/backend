package ru.at0m1cc.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.at0m1cc.db.UserPC;
import ru.at0m1cc.dto.StatusCode;
import ru.at0m1cc.dto.StatusDTO;
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
    @GetMapping("/showUsersPC")
    @CrossOrigin("*")
    public List<UserPC> showUsers(HttpSession session) {
        if(session.getAttribute("login") != null) {
            return javaControlService.showUsers();
        }
        return null;
    }
    /**
     * API для обработки команд
     * */
    @PostMapping("/controlUserPC")
    @CrossOrigin("*")
    public ResponseEntity<StatusDTO> control(HttpSession session, @RequestParam("ipAddress") String ipAddress, @RequestParam("port") String port, @RequestParam("command") String command) {
        if(session.getAttribute("login") != null) {
            return  javaControlService.sendCommandToUserPC(ipAddress, port, command);
        }
        return ResponseEntity.status(401).build();
    }
    /**
     * API для добавления ПК пользователя в бд
     */
    @PostMapping("/addUserPC")
    @CrossOrigin("*")
    public ResponseEntity<StatusDTO> addUser(HttpSession session, @RequestParam("ipAddress") String ipAddress, @RequestParam("port") String port){
        if(port.isEmpty()){ //Если порт пустой, то ставим по дефолту
            port = "5556";
        }
        if(session.getAttribute("login") != null) {
           return javaControlService.addUser(ipAddress, port);
        }
        return ResponseEntity.status(401).body(new StatusDTO(StatusCode.ERROR));
    }
    /**
     * API для удаления ПК пользователя из бд
     */
    @PostMapping("/deleteUserPC")
    @CrossOrigin("*")
    public ResponseEntity<StatusDTO> deleteUser(HttpSession session, @RequestParam("ipAddress") String ipAddress, @RequestParam("port") String port){
        if(session.getAttribute("login") != null) {
            return javaControlService.deleteUser(ipAddress, port);
        }
        return ResponseEntity.status(401).body(new StatusDTO(StatusCode.ERROR));
    }
}
