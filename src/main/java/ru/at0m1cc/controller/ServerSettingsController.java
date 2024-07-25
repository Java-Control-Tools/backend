package ru.at0m1cc.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.at0m1cc.log.Logger;
import ru.at0m1cc.service.ServerSettingsService;


@RestController
@RequestMapping("/api/server")
public class ServerSettingsController {
    private final ServerSettingsService serverSettingsService;
    private final Logger logger;

    @Autowired
    public ServerSettingsController(ServerSettingsService serverSettingsService, Logger logger) {
        this.serverSettingsService = serverSettingsService;
        this.logger = logger;
    }
    @PostMapping("/reboot")
    @CrossOrigin("*")
    public void reboot(HttpSession session, @RequestHeader(value = "User-Agent") String userAgent) {
        if (session.getAttribute("login") != null) {
            logger.writeRebootServerLog(userAgent);
            serverSettingsService.reboot();
        }
    }
}
