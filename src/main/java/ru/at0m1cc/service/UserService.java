package ru.at0m1cc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.at0m1cc.config.UserInfo;
import ru.at0m1cc.db.User;
import ru.at0m1cc.dto.StatusCode;
import ru.at0m1cc.dto.StatusDTO;
import ru.at0m1cc.repository.UserRepository;

import java.util.Optional;

/**
 * Сервис для работы с данными из репозитория
 * @author at0m1cc
 * @version 1.1
 * */
@Service
public class UserService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserService() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public StatusDTO changePassword(String username, String oldPassword, String newPassword) {
        Optional<User> user = userRepository.findByUsername(username);
        if(bCryptPasswordEncoder.matches(oldPassword, user.get().getPassword())) { //Сравнение пароля который пришёл с хэшем из бд
            user.get().setPassword(bCryptPasswordEncoder.encode(newPassword));
            userRepository.save(user.get());
            return new StatusDTO(StatusCode.OK);
        }
        return new StatusDTO(StatusCode.ERROR);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //Выгурзить инфу о юзере в опшионал
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(UserInfo::new).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
