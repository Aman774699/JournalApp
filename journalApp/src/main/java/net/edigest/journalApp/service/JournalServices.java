package net.edigest.journalApp.service;

import jakarta.transaction.Transactional;
import net.edigest.journalApp.entity.JournalEntity;
import net.edigest.journalApp.entity.UserEntity;
import net.edigest.journalApp.repository.JournalRepository;
import net.edigest.journalApp.repository.UserRepositor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JournalServices {
    @Autowired
    JournalRepository journalRepository;
    @Autowired
    UserRepositor userRepositor;

    @Transactional
    public Boolean post(JournalEntity journalEntity, String username)
    {
       UserEntity userEntity=userRepositor.findByUsername(username);
       if(userEntity==null) {
           return false;
       }
        journalEntity.setUserEntity(userEntity);
        userEntity.getJournalEntityList().add(journalEntity);
       userRepositor.save(userEntity);
       return true;
    }

    public  Boolean post(JournalEntity journalEntity)
    {
        journalRepository.save(journalEntity);
        return true;
    }


    public List<JournalEntity> getallByuserName(Iterable<Integer> Id) {
        List<JournalEntity>journalEntities=new ArrayList<>();
        journalEntities=journalRepository.findAllById(Id);
        return journalEntities;
    }

    public Optional<JournalEntity> get(Integer id) {
        return journalRepository.findById(id);
    }


    public boolean updateById(int id, JournalEntity journalEntity) {
        journalEntity.setId(id);
        try {
            journalRepository.save(journalEntity);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
         return true;
    }

    public boolean deleteById(int id) {
        journalRepository.deleteById(id);
        return true;
    }


    public Optional<JournalEntity> findById(int id) {
        return journalRepository.findById(id);
    }
}
