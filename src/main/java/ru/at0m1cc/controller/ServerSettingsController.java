package ru.at0m1cc.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.at0m1cc.log.Logger;
import ru.at0m1cc.service.ServerSettingsService;

import java.io.IOException;
import java.util.List;


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

    @GetMapping("/showLogs")
    @CrossOrigin("*")
    public List<String> showLogs(HttpSession session) throws IOException {
        if (session.getAttribute("login") != null) {
            return serverSettingsService.showLogs();
        }
        return null;
    }
}
