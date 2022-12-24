package its.extratech.FutureTravel.serviceTests;

import its.extratech.FutureTravel.servicies.implementations.RecordServiceImpl;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RecordServiceTest {

    @Autowired
    RecordServiceImpl recordServiceImpl;

}
