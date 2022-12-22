package its.extratech.FutureTravel.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class RecordFrontEndDto {

    public String Territorio;
    public String Residenza_clienti;
    public String Tipologia_esercizio;
    public String Time;
    public int Arrivi;
    public int Presenze;
}
