package net.edigest.journalApp.controller;

import net.edigest.journalApp.entity.UserEntity;
import net.edigest.journalApp.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @GetMapping("/get-all-user")
    public ResponseEntity<?>getall()
    {
        List<UserEntity> userEntity=userService.getall();
        if (userEntity.isEmpty())
        {
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
        }
        else
        {
            return ResponseEntity.of(Optional.of((userEntity)));
        }
    }
    @PostMapping("/New-Admin")
    public ResponseEntity<UserEntity>createNew(@RequestBody UserEntity userEntity)
    {
        userEntity.setRole("ADMIN");
        userService.save(userEntity);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).build();
    }
}
