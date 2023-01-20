package its.extratech.FutureTravel.dtos.request;

import lombok.*;
@Getter
@Setter
public class FintechRequest {



    public String provincia;
    public String residenzaClienti;
    public String tipoAlloggio;
    public String time;
    public int value;


    public String toString() {
        return "FintechRequest{" +
                "provincia='" + this.provincia + '\'' +
                ", residenzaClienti='" + this.residenzaClienti + '\'' +
                ", tipoAlloggio='" + this.tipoAlloggio + '\'' +
                ", time='" + this.time + '\'' +
                ", presenze=" + this.value +
                '}';
    }


}
