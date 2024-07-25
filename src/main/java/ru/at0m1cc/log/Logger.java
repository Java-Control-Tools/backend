package ru.at0m1cc.log;

import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Component
public class Logger {
    private final FileWriter log = new FileWriter("log.log", true);

    public Logger() throws IOException {
    }

    public void writeServerStartLog(){
        try{
            log.write("[ "+ new Date() +" ] SERVER STARTED" + "\n");
            log.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void writeRebootServerLog(String userInfo){
        try{
            log.write("[ "+ new Date() +" ] SERVER REBOOT, USER-AGENT: " + "\n");
            log.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeLoginLog(String userInfo) {
        try {
            log.write("[ "+ new Date() +" ] LOGGED IN ACCOUNT, USER-AGENT: " + userInfo + "\n");
            log.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void writeChangePasswordLog(String userInfo) {
         try {
            log.write("[ "+ new Date() +" ] PASSWORD CHANGED, USER-AGENT: " + userInfo + "\n");
            log.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
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
