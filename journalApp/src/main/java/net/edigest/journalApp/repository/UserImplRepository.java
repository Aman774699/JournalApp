package net.edigest.journalApp.repository;

import net.edigest.journalApp.entity.UserEntity;
import net.edigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserImplRepository {
    @Autowired
    UserService userService;

    public List<UserEntity> getUserForSA()
    {
      List<UserEntity>result=userService.getall();
      List<UserEntity>ans=result.stream().filter(x->x.getEmail()!=null&&x.getSentimantalanalysis()==true&&x.getRole()=="USER").collect(Collectors.toList());
      return ans;
    }
}
