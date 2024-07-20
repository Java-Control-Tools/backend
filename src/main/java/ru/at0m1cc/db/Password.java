package ru.at0m1cc.db;

import jakarta.persistence.*;

/**
 * Сущность. Представляет собой объект таблицы PASSWORD
 * @author at0m1cc
 * @version 1.1
 * */
@Entity
@Table(name = "PASSWORD")
public class Password {
    /**
     * ID поле соответсвующее полю в таблице, так же как и в таблице, данное поле автоинкрементируется
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    /**
     * Password поле соответсвующее полю в таблице
     * */
    @Column(name = "PASSWORD")
    private String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Password(String password) {
        this.password = password;
    }
    /**
     * Необходимо оставить конструктор без параметров, для успешного создания сущности
     * */
    public Password() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
