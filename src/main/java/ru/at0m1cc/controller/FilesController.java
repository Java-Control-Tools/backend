package ru.at0m1cc.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
/**
 *  API для получения файла изображения с рабочего стола ПК
 * @author at0m1cc
 * @version 1.1
 * */
@RestController
@RequestMapping("/files")
public class FilesController {

    @GetMapping("/photo")
    public ResponseEntity<?> getPhoto(HttpSession session) {
        try {
            return ResponseEntity.ok().body(Files.readAllBytes(Path.of("screen.png")));
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
