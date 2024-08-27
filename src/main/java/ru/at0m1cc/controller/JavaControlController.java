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
    public List<UserPC> showUsers() {
        return javaControlService.showUsers();
    }
    /**
     * API для обработки команд
     * */
    @PostMapping("/controlUserPC")
    @CrossOrigin("*")
    public ResponseEntity<StatusDTO> control(@RequestParam("ipAddress") String ipAddress, @RequestParam("port") String port, @RequestParam("command") String command) {
        return  javaControlService.sendCommandToUserPC(ipAddress, port, command);
    }
    /**
     * API для добавления ПК пользователя в бд
     */
    @PostMapping("/addUserPC")
    @CrossOrigin("*")
    public ResponseEntity<StatusDTO> addUser(@RequestParam("ipAddress") String ipAddress, @RequestParam("port") String port){
        if(port.isEmpty()){ //Если порт пустой, то ставим по дефолту
            port = "5556";
        }
        return javaControlService.addUser(ipAddress, port);
    }
    /**
     * API для удаления ПК пользователя из бд
     */
    @PostMapping("/deleteUserPC")
    @CrossOrigin("*")
    public ResponseEntity<StatusDTO> deleteUser(@RequestParam("ipAddress") String ipAddress, @RequestParam("port") String port){
        return javaControlService.deleteUser(ipAddress, port);
    }
    /**
     * API для обновления ПК пользователя в бд
     */
    @PostMapping("/updateUserPC")
    @CrossOrigin("*")
    public ResponseEntity<StatusDTO> updateUser(@RequestParam("oldIp") String oldIpAddress, @RequestParam("oldPort") String oldPort,
                                                @RequestParam("newIp") String newIpAddress, @RequestParam("newPort") String newPort){
        return javaControlService.updateUser(oldIpAddress, oldPort, newIpAddress, newPort);
    }
}
