package ru.at0m1cc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.at0m1cc.db.User;
import ru.at0m1cc.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserEditController {
    private final UserService userService;
    @Autowired
    public UserEditController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/showUsers")
    @CrossOrigin("*")
    public List<User> showUsers() {
        return userService.getUsers();
    }
}
