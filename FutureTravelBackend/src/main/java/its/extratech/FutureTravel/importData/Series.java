package its.extratech.FutureTravel.importData;

import its.extratech.FutureTravel.entities.*;
import its.extratech.FutureTravel.entities.Record;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Series {
    public SeriesKey seriesKey;
    public List<Obs> obsList;

    @Override
    public String toString() {
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



}
