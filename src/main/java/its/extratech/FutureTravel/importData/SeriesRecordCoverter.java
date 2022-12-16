package its.extratech.FutureTravel.importData;

import its.extratech.FutureTravel.entities.Contesto;
import its.extratech.FutureTravel.entities.Provincia;
import its.extratech.FutureTravel.entities.Record;

import java.util.ArrayList;
import java.util.List;

public class SeriesRecordCoverter {

    public List<Record> toSeriesToRecordList(List<Series> seriesList){
        List<Record> records = new ArrayList<>();

        for (Series series : seriesList) {
            Record record = new Record();
    // TODO
            /*Provincia provincia = new Provincia();
            provincia.setNomeProvincia();
            Contesto contesto = new Contesto();
            contesto.setProvincia();*/
        }

        return records;
    }
}
