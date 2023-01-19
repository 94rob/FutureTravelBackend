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


}
