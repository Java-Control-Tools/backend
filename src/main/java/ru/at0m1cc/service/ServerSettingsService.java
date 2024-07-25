package ru.at0m1cc.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.at0m1cc.log.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class ServerSettingsService {

    public void reboot(){
        try {
            Runtime.getRuntime().exec("reboot");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<String> showLogs() throws IOException {
        return new Logger().showLog();
    }


}
