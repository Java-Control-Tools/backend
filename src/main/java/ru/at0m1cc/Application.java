package ru.at0m1cc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.at0m1cc.log.Logger;

import java.io.IOException;


@SpringBootApplication
public class Application {
    public static void main(String[] args) throws IOException {
        Logger log = new Logger();
        log.writeServerStartLog();
        SpringApplication.run(Application.class, args);
    }
}