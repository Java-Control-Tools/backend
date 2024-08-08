package ru.at0m1cc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.at0m1cc.db.UserPC;
/**
 * Репозиторий, который наследуется от JpaRepository и представляет собой набор операций с базой данных
 * @author at0m1cc
 * @version 1.1
 * */
@Repository
public interface UserPCRepository extends JpaRepository<UserPC, Long> {
    /**
     * Объявление сигнатуры метода existsByIpAddressAndPort, который в дальнейшем по средствам spring будет выполнять запрос на
     * Поиск наличия аргумента Ip и Port
     * */
    boolean existsByIpAddressAndPort(String ipAddress, String port);
    /**
     * Объявление сигнатуры метода findByIpAddressAndPort, который в дальнейшем по средствам spring будет выполнять запрос на
     * Поиск аргумента Ip и Port
     * */
    UserPC findByIpAddressAndPort(String ipAddress, String port);
}
