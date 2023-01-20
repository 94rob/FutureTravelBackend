package its.extratech.FutureTravel.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class RecordDtoCompleto {

    public String provincia;
    public String residenzaClienti;
    public String tipoAlloggio;
    public String time;
    public int arrivi;
    public int presenze;
    public char tipoDato;
}
