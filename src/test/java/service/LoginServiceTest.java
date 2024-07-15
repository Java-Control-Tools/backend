package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.at0m1cc.repository.LoginRepository;
import ru.at0m1cc.service.LoginService;

public class LoginServiceTest {
    @Test
    public void testLogin() {
        LoginService loginService = new LoginService(new LoginRepository());
        Assertions.assertTrue(loginService.login("password"));
    }
}
