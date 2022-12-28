package its.extratech.FutureTravel.insertDataTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import its.extratech.FutureTravel.FutureTravelApplication;
import its.extratech.FutureTravel.entities.Record;
import its.extratech.FutureTravel.importData.*;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.w3c.dom.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FetchIstatAPITest {

    private XMLIstatReader xmlIstatReader = new XMLIstatReader();
    private String url = "http://sdmx.istat.it/SDMXWS/rest/data/122_54/M.551_553.N.IT.ITF31.TOT.HOTELLIKE.NI.../?";
    private ImportDataUtils importDataUtils = new ImportDataUtils();

    @Test
    @Order(1)
    public void testUrlCreation(){
        assertEquals(url, xmlIstatReader.createUrlForIstatApi("IT", "ITF31", "NI", "HOTELLIKE", ""), "Url corretto");
    }

    @Test
    @Order(2)
    public void testFetchIstatApiReturnsString(){
        assertThat(xmlIstatReader.fetchIstatApi(url))
                .isInstanceOf(String.class);
    }

    String xmlString = "<generic:Series>" +
                         "<generic:SeriesKey>" +
                            "<generic:Value id='FREQ' value='M' />" +
                                "<generic:Value id='ATECO_2007' value='551_553' />" +
                                "<generic:Value id='ADJUSTMENT' value='N' />" +
                                "<generic:Value id='PAESE_RES' value='IT' />" +
                                "<generic:Value id='ITTER107' value='ITF31' />" +
                                "<generic:Value id='CLAS_DIM' value='TOT' />" +
                                "<generic:Value id='TIPO_ESERCIZIO' value='HOTELLIKE' />" +
                                "<generic:Value id='INDS' value='NI' />" +
                                "<generic:Value id='GRADO_URB' value='ALL' />" +
                                "<generic:Value id='LOCALITA_COST' value='ALL' />" +
                                "<generic:Value id='TYPE_LOC' value='ALL' />" +
                            "</generic:SeriesKey>" +
                            "<generic:Obs>" +
                                "<generic:ObsDimension id='TIME_PERIOD' value='2008-01' />" +
                                "<generic:ObsValue value='36101' />" +
                            "</generic:Obs>" +
                            "<generic:Obs>" +
                                "<generic:ObsDimension id='TIME_PERIOD' value='2008-10' />" +
                                "<generic:ObsValue value='50153' />" +
                            "</generic:Obs>" +
                            "<generic:Obs>" +
                                "<generic:ObsDimension id='TIME_PERIOD' value='2008-11' />" +
                                "<generic:ObsValue value='46753' />" +
                            "</generic:Obs>" +
                        "</generic:Series>";

    @Test
    @Order(3)
    public void testFromStringToXMLDocumentReturnsDocument() throws IOException {
        assertThat(this.importDataUtils.fromStringToXMLDocument(xmlString))
                .isInstanceOf(Document.class);
    }

    Document doc;

    {
        try {
            doc = this.importDataUtils.fromStringToXMLDocument(xmlString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(4)
    public void testGetSeriesReturnsListOfSeries(){
        assertThat(this.xmlIstatReader.getSeries(doc).get(0))
                .isInstanceOf(Series.class);
    }

    @Test
    @Order(5)
    public void testGetSeriesReturnsListOfSize1(){
        assertEquals(1, this.xmlIstatReader.getSeries(doc).size());
    }

    public Series series = this.xmlIstatReader.getSeries(doc).get(0);

    @Test
    @Order(6)
    public void testGetSeriesReturnsCorrectSeries(){
        assertEquals("ITF31", series.seriesKey.codiceProvincia);
        assertEquals("NI", series.seriesKey.indicatore);
        assertEquals("IT", series.seriesKey.residenzaClienti);
        assertEquals("HOTELLIKE", series.seriesKey.tipoAlloggio);

        assertEquals("2008-01", series.obsList.get(0).mese);
        assertEquals("36101", series.obsList.get(0).value);

        assertEquals("2008-10", series.obsList.get(1).mese);
        assertEquals("50153", series.obsList.get(1).value);

        assertEquals("2008-11", series.obsList.get(2).mese);
        assertEquals("46753", series.obsList.get(2).value);
    }

    @Test
    @Order(7)
    public void testSeriesToRecordListReturnsRecordList(){
        assertThat(this.importDataUtils.fromSeriesToRecordList(series).get(0))
                .isInstanceOf(Record.class);
    }

    public List<Record> recordList = this.importDataUtils.fromSeriesToRecordList(series);

    @Test
    @Order(8)
    public void testRecordListHasRightSize(){
        assertEquals(3, recordList.size());
    }

    @Test
    @Order(9)
    public void testRecordsAreCorrect(){
        assertEquals("ITF31", recordList.get(0).contesto.provincia.id );
        assertEquals("IT", recordList.get(0).contesto.residenzaClienti.id );
        assertEquals("HOTELLIKE", recordList.get(0).contesto.tipoAlloggio.id );

        assertEquals(36101, recordList.get(0).presenze);
        assertEquals("2008-01", recordList.get(0).time);

        assertEquals("ITF31", recordList.get(1).contesto.provincia.id );
        assertEquals("IT", recordList.get(1).contesto.residenzaClienti.id );
        assertEquals("HOTELLIKE", recordList.get(1).contesto.tipoAlloggio.id );

        assertEquals(50153, recordList.get(1).presenze);
        assertEquals("2008-10", recordList.get(1).time);

        assertEquals("ITF31", recordList.get(2).contesto.provincia.id );
        assertEquals("IT", recordList.get(2).contesto.residenzaClienti.id );
        assertEquals("HOTELLIKE", recordList.get(2).contesto.tipoAlloggio.id );

        assertEquals(46753, recordList.get(2).presenze);
        assertEquals("2008-11", recordList.get(2).time);

    }


    private static Series seriesArrivi = new Series();
    private static Series seriesPresenze = new Series();



    static {
        SeriesKey seriesKeyArrivi = new SeriesKey("ITF31", "AR", "HOTELLIKE", "IT");
        SeriesKey seriesKeyPresenze = new SeriesKey("ITF31", "NI", "HOTELLIKE", "IT");

        Obs obs1 = new Obs("2008-01", "123");
        Obs obs2 = new Obs("2008-01", "234");
        Obs obs3 = new Obs("2008-02", "456");
        Obs obs4 = new Obs("2008-03", "678");

        List<Obs> arriviObsList = new ArrayList<>();
        arriviObsList.add(obs1);
        arriviObsList.add(obs3);

        List<Obs> presenzeObsList = new ArrayList<>();
        presenzeObsList.add(obs2);
        presenzeObsList.add(obs4);

        seriesArrivi.setSeriesKey(seriesKeyArrivi);
        seriesArrivi.setObsList(arriviObsList);

        seriesPresenze.setSeriesKey(seriesKeyPresenze);
        seriesPresenze.setObsList(presenzeObsList);
    }

    List<Record> arriviRecordList = this.importDataUtils.fromSeriesToRecordList(seriesArrivi);
    List<Record> presenzeRecordList = this.importDataUtils.fromSeriesToRecordList(seriesPresenze);

    @Test
    @Order(10)
    public void textMixSeriesWithSameSeriesKeyReturnsRightSize(){
        List<Record> result = this.xmlIstatReader.mixRecordListWithSameContesto(arriviRecordList, presenzeRecordList);
        assertEquals(3, result.size());
    }

    @Test
    @Order(11)
    public void testMixSeriesWithSameSeriesKeyReturnsRightRecords(){
        List<Record> result = this.xmlIstatReader.mixRecordListWithSameContesto(arriviRecordList, presenzeRecordList);

        assertEquals("2008-01", result.get(0).time);
        assertEquals(123, result.get(0).arrivi);
        assertEquals(234, result.get(0).presenze);

        assertEquals("2008-02", result.get(1).time);
        assertEquals(456, result.get(1).arrivi);
        assertEquals(0, result.get(1).presenze);

        assertEquals("2008-03", result.get(2).time);
        assertEquals(0, result.get(2).arrivi);
        assertEquals(678, result.get(2).presenze);

    }
}
