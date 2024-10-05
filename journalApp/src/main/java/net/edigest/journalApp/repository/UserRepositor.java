package net.edigest.journalApp.repository;

import net.edigest.journalApp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositor extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);

    void deleteByUsername(String username);
}
