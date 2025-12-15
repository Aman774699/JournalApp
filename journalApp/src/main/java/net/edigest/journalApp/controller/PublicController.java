package net.edigest.journalApp.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import net.edigest.journalApp.entity.UserEntity;
import net.edigest.journalApp.service.UserDetail;
import net.edigest.journalApp.service.UserDetailServiceimpl;
import net.edigest.journalApp.service.UserService;
import net.edigest.journalApp.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserDetailServiceimpl userDetailServiceimpl;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    RedisTemplate redisTemplate;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody UserEntity userEntity) {

        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRole("USER");
        if (userService.save(userEntity)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserEntity userEntity) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userEntity.getUsername(), userEntity.getPassword()));
            UserDetails userDetail = userDetailServiceimpl.loadUserByUsername(userEntity.getUsername());
            String jwt = jwtUtil.generateToken(userDetail.getUsername());
            redisTemplate.opsForValue().set(
                    "session:jwt:" + jwt,
                    userDetail.getUsername(),
                    50, TimeUnit.MINUTES   // same as JWT expiry
            );

            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception is...", e);
            return new ResponseEntity<>("Error Generated", HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);

        redisTemplate.delete("session:jwt:" + token);

        return ResponseEntity.ok("Logged out successfully");
    }

}
