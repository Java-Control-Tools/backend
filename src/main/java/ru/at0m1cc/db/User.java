package ru.at0m1cc.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Сущность. Представляет собой объект таблицы PASSWORD
 * @author at0m1cc
 * @version 1.1
 * */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {
    /**
     * ID поле соответсвующее полю в таблице, так же как и в таблице, данное поле автоинкрементируется
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "USERNAME")
    private String username;
    /**
     * Password поле соответсвующее полю в таблице
     * */
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ROLE")
    private String role;
    public User(String username, String password, String role) {
        this.password = password;
        this.username = username;
        this.role = role;
    }

}
