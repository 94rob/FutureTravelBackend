package its.extratech.FutureTravel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "CONTESTO")
public class Contesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONTESTO")
    public long id;

    @JoinColumn(name = "PROVINCIA", referencedColumnName = "ID_PROVINCIA")
    @ManyToOne
    public Provincia provincia;

    @JoinColumn (name = "TIPO_ALLOGGIO", referencedColumnName = "ID_ALLOGGIO")
    @ManyToOne
    public TipoAlloggio tipoAlloggio;

    @JoinColumn (name = "RESIDENZA_CLIENTI", referencedColumnName = "ID_RESIDENZA")
    @ManyToOne
    public ResidenzaClienti residenzaClienti;

    @OneToMany(mappedBy = "contesto", fetch = FetchType.LAZY)
    public List<Record> records;


    public boolean equalsTo(Contesto c){
        return ((Objects.equals(c.getProvincia().getId(), provincia.getId())) &&
                (Objects.equals(c.getResidenzaClienti().getId(), residenzaClienti.getId())) &&
                (Objects.equals(c.getTipoAlloggio().getId(), tipoAlloggio.getId())));
    }

}
