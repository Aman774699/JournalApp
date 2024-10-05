package net.edigest.journalApp.controller;
import net.edigest.journalApp.api.response.WeatherResponse;
import net.edigest.journalApp.entity.UserEntity;
import net.edigest.journalApp.repository.UserImplRepository;
import net.edigest.journalApp.scheduler.UserScheduler;
import net.edigest.journalApp.service.EmailService;
import net.edigest.journalApp.service.UserService;
import net.edigest.journalApp.service.WheatherApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    UserService userService;
    @Autowired
    UserImplRepository userImplRepository;

    @Autowired
    WheatherApiService wheatherApiService;

    @Autowired
    EmailService emailService;

    @Autowired
    UserScheduler userScheduler;

    @GetMapping("/get")
    public ResponseEntity<UserEntity> getByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity userEntity = userService.findByUsername(username);
        if (userEntity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.of(Optional.of(userEntity));
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody UserEntity userEntity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity userEntity1 = userService.findByUsername(username);
        Optional<UserEntity> userEntity2 = userService.findById(userEntity1.getUser_id());
        if (userEntity2.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            try {
                UserEntity userEntity3 = userEntity2.get();
                userEntity3.setUsername(userEntity.getUsername());
                userEntity3.setPassword(userEntity.getPassword());
                userService.save(userEntity3);
                return ResponseEntity.of(Optional.ofNullable(userEntity3));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
            }
        }
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity userEntity1 = userService.findByUsername(username);
//        Optional<UserEntity> userEntity2 = userService.findById(userEntity1.getUser_id());
        if (userEntity1==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            userService.deleteById(userEntity1.getUser_id());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

    @GetMapping("/wheather/{City}")
    public String  getWheather(@PathVariable("City") String city)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        WeatherResponse weatherResponse=wheatherApiService.getWeather(city);
        return "Hii "+username+" The Current Temperature is  "+weatherResponse.getCurrent().temperature;
    }

    @GetMapping("/get/Sentimental")

    public  void getuser()
    {
        List<UserEntity> result=userImplRepository.getUserForSA();
        emailService.sendMail("amanvishwakarma922@gmail.com","Application for java developer","I am apply for java developer");
       userScheduler.getSentimantaluser();
    }
}
