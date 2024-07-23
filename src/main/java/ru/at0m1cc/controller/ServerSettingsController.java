package ru.at0m1cc.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.at0m1cc.service.ServerSettingsService;

@RestController
@RequestMapping("/api/server")
public class ServerSettingsController {
    private final ServerSettingsService serverSettingsService;

    @Autowired
    public ServerSettingsController(ServerSettingsService serverSettingsService) {
        this.serverSettingsService = serverSettingsService;
    }
    @PostMapping("/reboot")
    @CrossOrigin("*")
    public void reboot(HttpSession session) {
        if (session.getAttribute("login") != null) {
            serverSettingsService.reboot();
        }
    }
}
