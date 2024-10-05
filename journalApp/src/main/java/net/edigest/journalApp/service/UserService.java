package net.edigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.edigest.journalApp.controller.UserController;
import net.edigest.journalApp.entity.UserEntity;
import net.edigest.journalApp.repository.UserRepositor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserRepositor userRepositor;
//    private final Logger logger= LoggerFactory.getLogger(UserController.class); Instead of it we can use SLF4J
    public List<UserEntity> getall() {
        return userRepositor.findAll();
    }

    public boolean save(UserEntity userEntity) {
        try {
            userRepositor.save(userEntity);
           return true;
        }
        catch (Exception e)
        {
          log.error("Error occur at {}",userEntity.getUsername(),e);
          return  false;
        }
    }

    public Optional<UserEntity> findById(Integer id) {
        Optional<UserEntity> userEntity=userRepositor.findById(id);
        return userEntity;
    }

    public void deleteById(Integer id) {
        userRepositor.deleteById(id);
    }

    public UserEntity findByUsername(String username) {
        return userRepositor.findByUsername(username);
    }

    public void deleteByUsername(String username) {
        userRepositor.deleteByUsername(username);
    }
}
