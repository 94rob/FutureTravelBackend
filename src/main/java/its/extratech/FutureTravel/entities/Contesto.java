package its.extratech.FutureTravel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "CONTESTO")
public class Contesto {

    @Id
    public long id;

    @JoinColumn(name = "TERRITORIO", referencedColumnName = "ID")
    @ManyToOne
    public Provincia provincia;

    @JoinColumn (name = "TIPO_ALLOGGIO", referencedColumnName = "ID")
    @ManyToOne
    public TipoAlloggio tipoAlloggio;

    @JoinColumn (name = "RESIDENZA_CLIENTI", referencedColumnName = "ID")
    @ManyToOne
    public ResidenzaClienti residenzaClienti;

    @OneToMany(mappedBy = "contesto", fetch = FetchType.LAZY)
    public List<Record> records;

}
