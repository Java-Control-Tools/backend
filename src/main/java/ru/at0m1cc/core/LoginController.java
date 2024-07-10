package ru.at0m1cc.core;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Контроллер для отслеживания захода на Login
 * @author at0m1cc
 * @version 1.0
 * */
@Controller
public class LoginController {
    /**
     * Гениальное (Временное) решение использовать переменную для хранения пароля
     * */
    private static String password;

    /**
     * Конструктор
     * */
    public LoginController(@Value("password") String password) {
        LoginController.password = password;
    }
    /**
     * Аннотация @GetMapping принимает в себя GET запросы по определённому URL
     * В данном случае мы возвращаем в ответе пользователю страницу Login.html
     * */
    @GetMapping("/login")
    public String loginForm(HttpSession session) {
        //Проверка на то, есть ли атрибут loginStatus
        if (session.getAttribute("loginStatus") != null) {
            return "redirect:/main";
        }
        else {
            return "Login";
        }
    }
    /**
     * Аннотация @PostMapping вызывается когда мы нажимаем на форме Login.html кнопку Login
     * Класс loginForm проверяет верен ли пароль, если верен, он устанавливает новый атрибут для сессии - loginStatus со значением ok
     * Иначе вернёт нас на форму авторизации
     * */
    @PostMapping("/login")
    public String login(HttpSession session, @RequestParam("password") String password) {
        // Проверка переданного параметра password с действительным паролем
        if(password.equals(LoginController.password)) {
            session.setAttribute("loginStatus", "ok");
            return "redirect:/main"; //Если всё ок, то редиректим на главную страницу
        }
        else {
            return "redirect:/login"; // Если пароль не верный
        }
    }

    public static void setPassword(String password) {
        LoginController.password = password;
    }
}
