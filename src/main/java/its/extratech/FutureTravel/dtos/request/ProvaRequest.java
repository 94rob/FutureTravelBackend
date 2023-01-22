package its.extratech.FutureTravel.dtos.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProvaRequest {
    public String nome;
    public String cognome;

    public String toString(){
        return this.nome + " " + this.cognome + "!";
    }
}
