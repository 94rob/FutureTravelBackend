package its.extratech.FutureTravel.importData;

import its.extratech.FutureTravel.entities.*;
import its.extratech.FutureTravel.entities.Record;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Series {
    public SeriesKey seriesKey;
    public List<Obs> obsList;

    @Override
    public String toString(){
        return "SeriesKey: " +
                    "\n\tTerritorio: " + seriesKey.getCodiceProvincia() +
                    "\n\tTipo dato: " + seriesKey.getIndicatore() +
                    "\n\tTipo alloggio: " + seriesKey.getTipoAlloggio() +
                    "\n\tProvenienza clienti: " + seriesKey.getResidenzaClienti() +
                "\nObs (prime tre): " +
                    "\n\tPrima osservazione: " +
                        "\n\t\tPeriodo: " + obsList.get(0).getMese() +
                        "\n\t\tValore osservato: " + obsList.get(0).getValue() +
                    "\n\tSeconda osservazione: " +
                        "\n\t\tPeriodo: " + obsList.get(1).getMese() +
                        "\n\t\tValore osservato: " + obsList.get(1).getValue() +
                    "\n\tTerza osservazione: " +
                        "\n\t\tPeriodo: " + obsList.get(2).getMese() +
                        "\n\t\tValore osservato: " + obsList.get(2).getValue();
    }

    public List<Record> toRecordList(){
        Provincia provincia = new Provincia();
        provincia.setId(seriesKey.getCodiceProvincia());

        ResidenzaClienti residenzaClienti = new ResidenzaClienti();
        residenzaClienti.setId(seriesKey.getResidenzaClienti());

        TipoAlloggio tipoAlloggio = new TipoAlloggio();
        tipoAlloggio.setId(seriesKey.getTipoAlloggio());

        Contesto contesto = new Contesto();
        contesto.setProvincia(provincia);
        contesto.setResidenzaClienti(residenzaClienti);
        contesto.setTipoAlloggio(tipoAlloggio);

        Record record = new Record();
        List<Record> recordList = new ArrayList<>();

        for(Obs obs : obsList){
            record.setContesto(contesto);
            record.setTime(obs.getMese());

            try{
                int value = Integer.parseInt(obs.getValue());

                if (seriesKey.getIndicatore() == "NI") {
                    record.setPresenze(value);
                } else {
                    record.setArrivi(value);
                }

                recordList.add(record);
            } catch (NumberFormatException e){
                e.printStackTrace();
                continue;
            }

        }

        return recordList;

    }

}
