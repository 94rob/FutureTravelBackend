package its.extratech.FutureTravel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SetUpTest {

    @Autowired
    SetUp setUp;

    @Test
    public void stringToNextMonthTest(){
        Assertions.assertEquals("2008-02", setUp.considerNextMonth("2008-01"));
    }

    @Test
    public void stringToNextMonthTest2(){
        Assertions.assertEquals("2009-01", setUp.considerNextMonth("2008-12"));
    }

}
