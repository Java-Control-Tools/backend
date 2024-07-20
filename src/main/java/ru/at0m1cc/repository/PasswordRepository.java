package ru.at0m1cc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import ru.at0m1cc.db.Password;

import java.util.List;

/**
 * Репозиторий, который наследуется от JpaRepository и представляет собой набор операций с базой данных
 * @author at0m1cc
 * @version 1.1
 * */
@Repository
public interface PasswordRepository extends JpaRepository<Password, Long> {
    /**
     * Объявление сигнатуры метода findByPassword, который в дальнейшем по средствам spring будет выполнять запрос на
     * Поиск аргумента Password по столбцу Password
     * */
    List<Password> findByPassword(String password);
}
