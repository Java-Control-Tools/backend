package ru.at0m1cc.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ServerSettingsService {
    public void reboot(){
        try {
            Runtime.getRuntime().exec("reboot");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
