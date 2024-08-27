package ru.at0m1cc.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.at0m1cc.log.Logger;
import ru.at0m1cc.service.ServerSettingsService;

import java.io.IOException;
import java.util.List;

/**
 *  API для работы с сервером
 * @author at0m1cc
 * @version 1.1
 * */
@RestController
@RequestMapping("/api/server")
public class ServerSettingsController {
    private final ServerSettingsService serverSettingsService;
    private final Logger logger;
    /**
     * Конструктор с аннотацией Autowired для автоматического внедрения зависимостей
     * */
    @Autowired
    public ServerSettingsController(ServerSettingsService serverSettingsService, Logger logger) {
        this.serverSettingsService = serverSettingsService;
        this.logger = logger;
    }
    /**
     * API для перезагрузки сервера (LINUX ONLY)
     * */
    @PostMapping("/reboot")
    @CrossOrigin("*")
    public void reboot(@RequestHeader(value = "User-Agent") String userAgent) {
        logger.writeRebootServerLog(userAgent);
        serverSettingsService.reboot();
    }
    /**
     * API для вывода логов
     * */
    @GetMapping("/showLogs")
    @CrossOrigin("*")
    public List<String> showLogs(HttpSession session) throws IOException {
        return serverSettingsService.showLogs();
    }
}
