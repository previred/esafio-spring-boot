package co.com.task.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MicroserviceApplicationTests {

    @Test
    void mainTest() {
        MicroserviceApplication main = new MicroserviceApplication();
        Assertions.assertNotNull(main);
    }

}
