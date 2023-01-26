package its.extratech.FutureTravel.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecordDtoFintech {
    public String ITTER107;
    public String TIPO_ALLOGGIO2;
    public String ISO;
    public String indicatori;
    public String TIME;
    public String Value;

    @JsonProperty("ITTER107")
    public String getITTER107() {
        return ITTER107;
    }

    @JsonProperty("TIPO_ALLOGGIO2")
    public String getTIPO_ALLOGGIO2() {
        return TIPO_ALLOGGIO2;
    }

    @JsonProperty("ISO")
    public String getISO() {
        return ISO;
    }

    @JsonProperty("Indicatori")
    public String getIndicatori() {
        return indicatori;
    }

    @JsonProperty("TIME")
    public String getTIME() {
        return TIME;
    }

    @JsonProperty("Value")
    public String getValue() {
        return Value;
    }
}
