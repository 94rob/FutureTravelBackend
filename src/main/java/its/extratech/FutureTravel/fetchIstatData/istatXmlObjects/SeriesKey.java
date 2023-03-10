package its.extratech.FutureTravel.fetchIstatData.istatXmlObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class SeriesKey {
    public String frequenza;
    public String codiceProvincia;
    public String indicatore;
    public String tipoAlloggio;
    public String residenzaClienti;
}
