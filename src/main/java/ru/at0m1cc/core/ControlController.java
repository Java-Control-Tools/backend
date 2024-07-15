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

/**
 * Контроллер для отслеживания захода на /Control
 * @author at0m1cc
 * @version 1.0
 * */
@Deprecated
@Controller
public class ControlController {
    /**
     * Хранения ip полученного из JC
     * */
    private String ipAddress;
    /**
     * Маппинг для /Control GET
     * */
    @GetMapping("/Control")
    public String controlForm(HttpSession session, @RequestParam("ipAddress") String ipAddress, Model model, HttpServletRequest request) {
        this.ipAddress = ipAddress; // Принимаем параметр ipAddress из запроса
        if(session.getAttribute("loginStatus") != null) {
            model.addAttribute("ipAddress", this.ipAddress); // Передаём параметр ipAddress в модель для представления на html
            return "Control";
        }
        else {
            return "redirect:/login";
        }
    }
    /**
     * Маппинг для /Control POST
     * */
    @PostMapping("/Control")
    public String controlParam(HttpSession session, HttpServletRequest request) {
        if(request.getParameter("reboot") != null) {
            try {
                Socket socket = new Socket(ipAddress,5556);
                socket.getOutputStream().write("reboot".getBytes());
                socket.getOutputStream().flush();
                socket.getOutputStream().close();
                socket.close();
                return "redirect:/JC";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(request.getParameter("powerOff") != null) {
            try {
                Socket socket = new Socket(ipAddress,5556);
                socket.getOutputStream().write("powerOff".getBytes());
                socket.getOutputStream().flush();
                socket.getOutputStream().close();
                socket.close();
                return "redirect:/JC";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(request.getParameter("logOut") != null) {
            try {
                Socket socket = new Socket(ipAddress,5556);
                socket.getOutputStream().write("logOut".getBytes());
                socket.getOutputStream().flush();
                socket.getOutputStream().close();
                socket.close();
                return "redirect:/JC";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return "redirect:/JC";
    }
}
