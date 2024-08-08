package ru.at0m1cc.log;

import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 *  Логгер
 * @author at0m1cc
 * @version 1.1
 * */
@Component
public class Logger {
    /**
     * Файл с логами
     * */
    private final FileWriter log = new FileWriter("log.log", true);

    public Logger() throws IOException {
    }
    /**
     * Запись на запуск сервера
     * */
    public void writeServerStartLog(){
        try{
            log.write("[ "+ new Date() +" ] SERVER STARTED" + "\n");
            log.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Запись на перезагрузку сервера
     * */
    public void writeRebootServerLog(String userInfo){
        try{
            log.write("[ "+ new Date() +" ] SERVER REBOOT, USER-AGENT: " + "\n");
            log.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Запись на вход в аккаунт
     * */
    public void writeLoginLog(String userInfo) {
        try {
            log.write("[ "+ new Date() +" ] LOGGED IN ACCOUNT, USER-AGENT: " + userInfo + "\n");
            log.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Запись на изменение пароля
     * */
    public void writeChangePasswordLog(String userInfo) {
         try {
            log.write("[ "+ new Date() +" ] PASSWORD CHANGED, USER-AGENT: " + userInfo + "\n");
            log.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Вывод логов в виде листа
     * */
    public List<String> showLog() throws FileNotFoundException {
        FileReader logShow = new FileReader("log.log");
        Scanner logShowScanner = new Scanner(logShow);
        List<String> logLines = new ArrayList<>();
        for(;logShowScanner.hasNextLine();){
            String line = logShowScanner.nextLine();
            logLines.add(line);
        }
        logShowScanner.close();
        return logLines;
    }

}
