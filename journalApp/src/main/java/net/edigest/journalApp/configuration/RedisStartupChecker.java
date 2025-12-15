package net.edigest.journalApp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisStartupChecker {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

//    @EventListener(ApplicationReadyEvent.class)
//    public void setStartupKeyOnApplicationReady() {
//        String key = "Chirag";
//        String value = "Jain";
//
//        redisTemplate.opsForValue().set(key, value);
//        redisTemplate.opsForValue().get(value);
//        System.out.println("🎉 Redis Event Listener Check Successful!");
//        System.out.println("   Key Set: " + key);
//        System.out.println("   Value Set: " + redisTemplate.opsForValue().get(key));
//    }

}
