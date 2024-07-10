package ru.at0m1cc.core;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Класс который нужен для приёма запросов на / а так же для приёма запросов на /main
 * @author at0m1cc
 * @version 1.0
 * */
@Controller
public class MainController {
    /**
     * Маппинг для /
     * */
    @GetMapping("/")
    public String defaultForm(HttpSession session){
        //проверка наличия атрибута loginStatus
       if(session.getAttribute("loginStatus") != null){
           return "redirect:/main"; // Если есть такой атрибут мы перенаправляем на /main
       }
       else {
           return "redirect:/login"; // Иначе перенаправляем на /login
       }
    }
    /**
     * Маппинг для /main
     * */
    @GetMapping("/main")
    public String mainForm(HttpSession session, HttpServletRequest request) {
        //проверка наличия атрибута loginStatus
        if(session.getAttribute("loginStatus") != null){
            /*
             * На странице MainForm.html пресутсвуют несколько элементов, которые передают
             * различные GET параметры которые мы тут проверяем
             * */
            if(request.getParameter("logOut") != null){
                session.removeAttribute("loginStatus");
                return "redirect:/login";
            }
            if(request.getParameter("jC") != null){
                return "redirect:/JC";
            }
            if(request.getParameter("settings") != null){
                return "redirect:/Settings";
            }
            return "MainForm"; //Если нет параметров, то просто вернуть страницу
        }
        else { //Если loginStatus нет, то вернём страницу Login.html
            return "redirect:/login";
        }
    }

}
