package ru.at0m1cc.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.at0m1cc.db.Password;
import ru.at0m1cc.repository.PasswordRepository;

import java.util.List;

@Service
public class LoginService {
    private final PasswordRepository passwordRepository;

    @Autowired
    public LoginService(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    @PostConstruct
    public void postConstruct() {
        if(passwordRepository.count() == 0) {
            Password password = new Password();
            password.setPassword("password");
            passwordRepository.save(password);
        }
    }

    public boolean login(String password) {
        List<Password> passwords = passwordRepository.findByPassword(password);
        return !passwords.isEmpty();
    }
}
