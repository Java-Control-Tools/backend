package ru.at0m1cc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.at0m1cc.db.UserPC;

@Repository
public interface UserPCRepository extends JpaRepository<UserPC, Long> {
}
