package its.extratech.FutureTravel.serviceTests;

import its.extratech.FutureTravel.servicies.RecordService;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RecordServiceTest {

    @Autowired
    RecordService recordService;

}
