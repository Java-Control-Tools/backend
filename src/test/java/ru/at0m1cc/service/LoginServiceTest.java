package ru.at0m1cc.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.at0m1cc.repository.LoginRepository;


public class LoginServiceTest {
    private LoginService loginService;
    @BeforeEach
    public void setUp() {
        loginService = new LoginService(new LoginRepository());
    }
    @Test
    public void testLogin() {
        Assertions.assertTrue(loginService.login("password"));
    }
}
