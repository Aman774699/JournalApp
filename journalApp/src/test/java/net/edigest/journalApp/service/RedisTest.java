package net.edigest.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {
@Autowired
    private RedisTemplate redisTemplate;

@Test
    void demo()
{
    redisTemplate.opsForValue().set("gmail","Amanvishwakarma@gmail.com");
    String email= (String) redisTemplate.opsForValue().get("gmail");
    System.out.println(email);
}
}
