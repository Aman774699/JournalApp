package net.edigest.journalApp.service;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//
//@SpringBootTest
//public class RedisTest {
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Autowired
//    private RedisConnectionFactory factory;
//
//    @Test
//    void checkConnection() {
//        System.out.println("@#@#@#@@#@##@");
//        String str=factory.getConnection().getClientName();
//        System.out.println(factory.getConnection().getClientName());
//        System.out.println(factory.getConnection().toString());
//    }
//
////    @Test
////    void demo() {
////        redisTemplate.opsForValue().set("gmail", "Amanvishwakarma@gmail.com");
////        String email = (String) redisTemplate.opsForValue().get("gmail");
////        System.out.println(email);
////    }
//}

// package net.edigest.journalApp.service; (Assuming this is the correct package)

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate; // Injected RedisTemplate

    // ... (Your other injections and checkConnection method)

    @Test
    void demo() {
        // 1. SET the value
        redisTemplate.opsForValue().set("test_key", "Test successful!");

        // 2. GET the value
        String result = (String) redisTemplate.opsForValue().get("test_key");

        // 3. Print the result
        System.out.println("Result from Redis: " + result);

        // You can also assert the result in a real unit test
        // assert result.equals("Test successful!");
    }
}
