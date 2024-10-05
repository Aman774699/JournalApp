package net.edigest.journalApp;

import net.edigest.journalApp.entity.UserEntity;
import net.edigest.journalApp.repository.UserImplRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class JournalAppApplicationTests {

    @Autowired
    UserImplRepository userImplRepository;
	@Test
	void contextLoads() {
        List<UserEntity>result= userImplRepository.getUserForSA();
        System.out.println(result);
	}
}
