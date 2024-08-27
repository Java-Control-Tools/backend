package ru.at0m1cc.db;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Сущность. Представляет собой объект таблицы USER_PC
 * @author at0m1cc
 * @version 1.1
 * */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "USER_PC")
public class UserPC {
    /**
     * ID поле соответсвующее полю в таблице, так же как и в таблице, данное поле автоинкрементируется
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;
    /**
     * Поле IP Адреса, с регулярным выражением для защиты от неправильного/инъекций пользовательского ввода
     * */
    @Pattern(regexp = "^((25[0-5]|(2[0-4]|1\\d|[0-9]|)\\d)\\.?\\b){4}$") //Регулярка под IP
    @Column(name = "IP_ADDRESS")
    private String ipAddress;
    /**
     * Поле port, с регулярным выражением для защиты от неправильного/инъекций пользовательского ввода
     * */
    @Pattern(regexp = "^(([0-9]{1,4})|([1-5][0-9]{4})|(6[0-4][0-9]{3})|(65[0-4][0-9]{2})|(655[0-2][0-9])|(6553[0-5]))$") // Регулярка под порт
    @Column(name = "PORT")
    private String port;
    /**
     * Поле статуса ПК
     * */
    @Column(name = "STATUS")
    private String status;
    /**
     * Необходимо оставить конструктор без параметров, для успешного создания сущности
     * */
    public UserPC(String ipAddress, String port, String status) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.status = status;
    }

}
