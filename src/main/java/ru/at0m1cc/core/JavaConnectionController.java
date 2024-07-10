package ru.at0m1cc.core;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
/**
 * Контроллер для отслеживания захода на JC
 * @author at0m1cc
 * @version 1.0
 * */
@Controller
public class    JavaConnectionController {
    /**
     * Мапа для хранения IP адресов и портов тех ПК, которые хотя-бы 1 раз были активными
     * */
    private final Map<String, Integer> JCPool = new HashMap<>();
    /**
     * Маппинг для /JS
     * */
    @GetMapping("/JC")
    public String javaConnectionForm(HttpSession session, HttpServletRequest request, Model model) {
        //проверка наличия атрибута loginStatus
        if(session.getAttribute("loginStatus") != null) {
            //Обновление всех ПК в мапе (проверка на активность)
            for(Map.Entry<String, Integer> entry : JCPool.entrySet()) {
                if(!checkStatus(entry.getKey(), entry.getValue())) {
                    JCPool.remove(entry.getKey());
                }
            }
            // Передача мапы на фронт шаблонизатору
            model.addAttribute("infoPC", JCPool);
            return "JC";
        }
        else return "redirect:/login"; //Если loginStatus нет, то вернём страницу Login.html
    }
    /**
     * Обработка мапинга для /JCLeave
     * Решение посредственное (Для отдельной обработки без обновления и передачи мапы ПК)
     * */
    @GetMapping("/JCLeave")
    public String javaConnectionLeave(HttpSession session, HttpServletRequest request) {
        /*
         * На странице JC.html пресутсвуют несколько элементов, которые передают
         * различные GET параметры которые мы тут проверяем
         * */
        if(request.getParameter("back") != null){
            return "redirect:/main";
        }
        if (request.getParameter("logOut") != null) {
            session.removeAttribute("loginStatus");
            return "redirect:/login";
        }
        return null;
    }
    /**
     * Маппинг для /JS (Post)
     * Добавление и проверка ПК на активность
     * */
    @PostMapping("/JC")
    public String javaConnectionAdd(Model model, @RequestParam("ipAddress") String ip, @RequestParam("port") String port) {
        model.addAttribute("infoPC", JCPool); //Заранее передаём мапу для отображения на фронте
        if(checkStatus(ip, Integer.parseInt(port))){ //Метод, который проверяет активность ПК
            JCPool.put(ip, Integer.parseInt(port)); // Добавляем в мапу в случае активности
            model.addAttribute("infoPC", JCPool); //Передаём мапу для отображения на фронте
        }
        return "JC";
    }
    /**
     * Метод для проверки активности ПК
     * */
    private boolean checkStatus(String ip, int port) {
        try {
            Socket socket = new Socket(ip, port);
            socket.getOutputStream().write("checkStatus".getBytes());
            socket.getOutputStream().close();
            socket.close();
            return true;
        }
        catch (IOException e) {
            return false;
        }

    }

}