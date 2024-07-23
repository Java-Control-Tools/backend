package ru.at0m1cc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.at0m1cc.db.UserPC;

import java.util.List;

/**
 * Репозиторий, который наследуется от JpaRepository и представляет собой набор операций с базой данных
 * @author at0m1cc
 * @version 1.1
 * */
@Repository
public interface UserPCRepository extends JpaRepository<UserPC, Long> {
    boolean existsByIpAddressAndPort(String ipAddress, String port);
    UserPC findByIpAddressAndPort(String ipAddress, String port);
}
