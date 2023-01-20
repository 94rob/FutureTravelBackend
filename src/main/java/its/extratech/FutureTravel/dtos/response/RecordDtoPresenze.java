package its.extratech.FutureTravel.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecordDtoPresenze {

    public String provincia;
    public String residenzaClienti;
    public String tipoAlloggio;
    public String time;
    public char tipoDato;
    public int presenze;
}
