package its.extratech.FutureTravel.importData;

import its.extratech.FutureTravel.entities.*;
import its.extratech.FutureTravel.entities.Record;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ImportDataUtils {

    public List<Record> fromSeriesToRecordList(Series series) {

        List<Record> recordList = new ArrayList<>();
        // setto il contesto
        Provincia provincia = new Provincia();
        provincia.setId(series.seriesKey.getCodiceProvincia());

        ResidenzaClienti residenzaClienti = new ResidenzaClienti();
        residenzaClienti.setId(series.seriesKey.getResidenzaClienti());

        TipoAlloggio tipoAlloggio = new TipoAlloggio();
        tipoAlloggio.setId(series.seriesKey.getTipoAlloggio());

        Contesto contesto = new Contesto();
        contesto.setProvincia(provincia);
        contesto.setResidenzaClienti(residenzaClienti);
        contesto.setTipoAlloggio(tipoAlloggio);

        for (Obs obs : series.obsList) {
            Record record = new Record();
            record.setContesto(contesto);
            record.setTime(obs.getMese());
            record.setFrequenza(series.seriesKey.getFrequenza().charAt(0));

            int value = Integer.parseInt(obs.getValue());

            if (Objects.equals(series.seriesKey.getIndicatore(), "NI")) {
                record.setPresenze(value);
            } else {
                record.setArrivi(value);
            }

            recordList.add(record);
        }
        return recordList;
    }



    public Document fromStringToXMLDocument(String s) throws IOException {
        Document doc = null;
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/tmp/file.xml"));
        writer.write(s);
        writer.close();

        try {
            File xmlFile = new File("src/main/resources/tmp/file.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(xmlFile);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfigurationException: " + e);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        } catch (SAXException e) {
            System.out.println("SAXException: " + e);
        }

        return doc;
    }
}
