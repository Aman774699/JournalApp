package net.edigest.journalApp.service;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    UserService userService;
    @ParameterizedTest
    @CsvSource({
            "Aman","Arjun"
    })
    public void testfindByUsername(String name)
    {
    assertNotNull(userService.findByUsername(name));
    }
}
