package net.edigest.journalApp;

import net.edigest.journalApp.entity.UserEntity;
import net.edigest.journalApp.repository.UserImplRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@SpringBootTest
class JournalAppApplicationTests {

    @Autowired
    UserImplRepository userImplRepository;

    @Autowired
    RedisTemplate redisTemplate;

	@Test
	void contextLoads() {
        List<UserEntity>result= userImplRepository.getUserForSA();
        System.out.println(result);
	}
}
