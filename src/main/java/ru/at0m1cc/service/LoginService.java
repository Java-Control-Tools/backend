package ru.at0m1cc.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.at0m1cc.db.Password;
import ru.at0m1cc.repository.PasswordRepository;

import java.util.List;

/**
 * Сервис для работы с данными из репозитория
 * @author at0m1cc
 * @version 1.1
 * */
@Service
public class LoginService {
    /**
     * Репозиторий для обращения к таблице PASSWORD
     * */
    private final PasswordRepository passwordRepository;
    /**
     * Конструктор с аннотацией Autowired для автоматического внедрения зависимостей
     * */
    @Autowired
    public LoginService(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }
    /**
     * Метод, который выполнится при создании бина. В нём мы проверяем есть ли в таблице PASSWORD
     * Какие-либо записи, в случае отсутствия мы добавляем пароль по умолчанию
     * */
    @PostConstruct
    public void postConstruct() {
        if(passwordRepository.count() == 0) {
            Password password = new Password();
            password.setPassword("password");
            passwordRepository.save(password);
        }
    }
    /**
     * Метод проверки пароля
     * */
    public boolean login(String password) {
        List<Password> passwords = passwordRepository.findByPassword(password);
        return !passwords.isEmpty();
    }
}
