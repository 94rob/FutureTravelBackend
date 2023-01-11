package its.extratech.FutureTravel.repositoryTests;

import its.extratech.FutureTravel.entities.Contesto;
import its.extratech.FutureTravel.entities.Provincia;
import its.extratech.FutureTravel.entities.Record;
import its.extratech.FutureTravel.repositories.RecordRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.internal.MetadataImpl;
import org.hibernate.internal.SessionFactoryImpl;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RecordRepositoryTest {

    @Autowired
    private RecordRepository recordRepository;

    @Test
    @Order(1)
    public void testSelByIdProvinciaSinceDate(){
        Record record = this.recordRepository.selByIdProvinciaSinceDate("2009-01", "ITF31").get(0);
        assertEquals("2009-01", record.getTime());
        /*
        assertThat(record)
                .extracting(Record::getContesto)
                .extracting(Contesto::getProvincia)
                .extracting(Provincia::getId)
                .isEqualTo("ITF31");

         */
    }

    @Test
    @Order(2)
    public void testSelByIdProvinciaBetweenTwoDates(){
        List<Record> recordList = this.recordRepository.selByIdProvinciaBetweenTwoDates("2009-01", "2010-01", "ITF31");
        Record firstRecord = recordList.get(0);
        Record lastRecord = recordList.get(51);

        assertEquals("2009-01", firstRecord.getTime());
        /*
        assertThat(firstRecord)
                .extracting(Record::getContesto)
                .extracting(Contesto::getProvincia)
                .extracting(Provincia::getId)
                .isEqualTo("ITF31");

         */

        assertEquals("2010-01", lastRecord.getTime());
        /*
        assertThat(lastRecord)
                .extracting(Record::getContesto)
                .extracting(Contesto::getProvincia)
                .extracting(Provincia::getId)
                .isEqualTo("ITF31");

         */
    }
}
