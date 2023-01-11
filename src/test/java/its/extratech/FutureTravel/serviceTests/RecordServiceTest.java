package its.extratech.FutureTravel.serviceTests;

import its.extratech.FutureTravel.dtos.RecordDtoCompleto;
import its.extratech.FutureTravel.entities.*;
import its.extratech.FutureTravel.entities.Record;
import its.extratech.FutureTravel.repositories.RecordRepository;
import its.extratech.FutureTravel.servicies.implementations.RecordServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.RecordComponent;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class RecordServiceTest {

    @Autowired
    RecordServiceImpl recordService;

    @Autowired
    RecordRepository recordRepository;

    private List<Record> recordList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        Provincia p1 = new Provincia();
        p1.setId("ITF31");

        TipoAlloggio ta1 = new TipoAlloggio();
        ta1.setId("HOTELLIKE");

        ResidenzaClienti rc1 = new ResidenzaClienti();
        rc1.setId("IT");

        Contesto c1 = new Contesto();
        c1.setProvincia(p1);
        c1.setResidenzaClienti(rc1);
        c1.setTipoAlloggio(ta1);

        Record r1 = new Record();
        r1.setContesto(c1);
        r1.setTime("2020-01");
        r1.setArrivi(22);
        r1.setPresenze(55);

        Provincia p2 = new Provincia();
        p2.setId("ITF31");

        ResidenzaClienti rc2 = new ResidenzaClienti();
        rc2.setId("WRL_X_ITA");

        TipoAlloggio ta2 = new TipoAlloggio();
        ta2.setId("HOTELLIKE");

        Contesto c2 = new Contesto();
        c2.setProvincia(p2);
        c2.setResidenzaClienti(rc2);
        c2.setTipoAlloggio(ta2);

        Record r2 = new Record();
        r2.setContesto(c2);
        r2.setTime("2020-01");
        r2.setArrivi(13);
        r2.setPresenze(17);

        Provincia p3 = new Provincia();
        p3.setId("ITF32");

        TipoAlloggio ta3 = new TipoAlloggio();
        ta3.setId("HOTELLIKE");

        ResidenzaClienti rc3 = new ResidenzaClienti();
        rc3.setId("IT");

        Contesto c3 = new Contesto();
        c3.setProvincia(p3);
        c3.setResidenzaClienti(rc3);
        c3.setTipoAlloggio(ta3);

        Record r3 = new Record();
        r3.setContesto(c3);
        r3.setTime("2020-02");
        r3.setArrivi(4);
        r3.setPresenze(4);

        Provincia p4 = new Provincia();
        p4.setId("ITF31");

        TipoAlloggio ta4 = new TipoAlloggio();
        ta4.setId("OTHER");

        ResidenzaClienti rc4 = new ResidenzaClienti();
        rc4.setId("IT");

        Contesto c4 = new Contesto();
        c4.setProvincia(p4);
        c4.setResidenzaClienti(rc4);
        c4.setTipoAlloggio(ta4);

        Record r4 = new Record();
        r4.setContesto(c4);
        r4.setTime("2020-01");
        r4.setArrivi(10);
        r4.setPresenze(10);

        recordList.add(r1); // RECORD 1: ITF31 - HOTELLIKE - IT - 2020-01 - (22, 55)
        recordList.add(r2); // RECORD 2: ITF31 - HOTELLIKE - WRL_X_ITA - 2020-01 - (13, 17)
        recordList.add(r3); // RECORD 3: ITF32 - HOTELLIKE - IT - 2020-02 - (4, 4)
        recordList.add(r4); // RECORD 4: ITF31 - OTHER - IT - 2020-01 - (10, 10)

    }

    @Test
    public void testCollapseResidenzeClientiReturnsRightValue(){
        List<Record> finalRecordList = this.recordService.collapseResidenzeClienti(recordList);
        Assertions.assertEquals(35, finalRecordList.get(0).getArrivi());
        Assertions.assertEquals(72, finalRecordList.get(0).getPresenze());
    }

    @Test
    public void testCollapseResidenzeClientiReturnsRightListSize(){
        List<Record> finalRecordList = this.recordService.collapseResidenzeClienti(recordList);
        Assertions.assertEquals(2, finalRecordList.size());
    }

    @Test
    public void testCollapseTipoAlloggioReturnsRightValue(){
        List<Record> finalRecordList = this.recordService.collapseTipoAlloggio(recordList);
        Assertions.assertEquals(32, finalRecordList.get(0).getArrivi());
        Assertions.assertEquals(65, finalRecordList.get(0).getPresenze());
    }

    @Test
    public void testCollapseTipoAlloggioReturnsRightListSize(){
        List<Record> finalRecordList = this.recordService.collapseTipoAlloggio(recordList);
        Assertions.assertEquals(2, finalRecordList.size());
    }

    /* TODO
        Risolvere l'errore org.hibernate.LazyInitializationException: could not initialize proxy
        così si potranno eseguire i test per lo strato di persistenza e di service
    @Test
    public void testSelAbsoluteDataByIdProvincia(){
        List<Record> recordList = this.recordRepository.selByIdProvinciaSinceDate("2008-01", "ITF31");
        List<RecordDtoCompleto> recordDtoCompletoList = this.recordService.selAbsoluteDataByIdProvincia("2008-01", "ITF31");
        Assertions.assertEquals(recordList.size(), recordDtoCompletoList.size() / 4);

    }
    */


}
