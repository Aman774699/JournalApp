package net.edigest.journalApp.repository;

import net.edigest.journalApp.entity.APIs;
import net.edigest.journalApp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface APIsRepository extends JpaRepository<APIs,String> {
}
