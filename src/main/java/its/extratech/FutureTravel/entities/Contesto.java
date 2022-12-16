package its.extratech.FutureTravel.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "CONTESTO")
public class Contesto {

    @Id
    public long id;

    @Column (name = "TERRITORIO")
    public Provincia provincia;

    @Column (name = "TIPO_ALLOGGIO")
    public TipoAlloggio tipoAlloggio;

    @Column (name = "RESIDENZA_CLIENTI")
    public ResidenzaClienti residenzaClienti;
}
