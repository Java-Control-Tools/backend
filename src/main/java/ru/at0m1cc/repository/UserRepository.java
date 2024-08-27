package ru.at0m1cc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.at0m1cc.db.User;

import java.util.Optional;

/**
 * Репозиторий, который наследуется от JpaRepository и представляет собой набор операций с базой данных
 * @author at0m1cc
 * @version 1.1
 * */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Объявление сигнатуры метода findByPassword, который в дальнейшем по средствам spring будет выполнять запрос на
     * Поиск аргумента Password по столбцу Password
     * */
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndPassword(String username, String password);
}
