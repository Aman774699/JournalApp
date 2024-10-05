package net.edigest.journalApp.repository;

import net.edigest.journalApp.entity.JournalEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JournalRepository extends JpaRepository<JournalEntity,Integer> {


}
