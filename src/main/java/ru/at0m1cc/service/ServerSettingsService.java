package ru.at0m1cc.service;

import org.springframework.stereotype.Service;
import ru.at0m1cc.log.Logger;

import java.io.IOException;
import java.util.List;
/**
 * Сервис для работы с сервером
 * @author at0m1cc
 * @version 1.1
 * */
@Service
public class ServerSettingsService {
    /**
     * Метод для перезагрузки сервера
     * */
    public void reboot(){
        try {
            Runtime.getRuntime().exec("reboot");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Метод для показа логов сервера
     * */
    public List<String> showLogs() throws IOException {
        return new Logger().showLog();
    }


}
