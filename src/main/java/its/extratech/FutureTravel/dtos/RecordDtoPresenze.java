package its.extratech.FutureTravel.dtos;

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
    public int presenze;
}
