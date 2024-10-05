package net.edigest.journalApp.service;

import net.edigest.journalApp.entity.UserEntity;
import net.edigest.journalApp.repository.UserRepositor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServiceimpl implements UserDetailsService {
    @Autowired
    UserRepositor userRepositor;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user=userRepositor.findByUsername(username);
        if(user!=null)
        {
//            UserDetail userDetail=new UserDetail(user);
//            return userDetail;
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole()).build();//changes of this because of only get Role authorities
        }
        throw new UsernameNotFoundException("User name does not exist"+username);
    }
}
