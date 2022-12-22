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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONTESTO")
    public long id;

    @JoinColumn(name = "TERRITORIO", referencedColumnName = "ID_PROVINCIA")
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

    public boolean isEqualsTo(Contesto c){
        return ((c.getProvincia().getId() == provincia.getId()) &&
                (c.getResidenzaClienti().getId() == residenzaClienti.getId()) &&
                (c.getTipoAlloggio().getId() == tipoAlloggio.getId()));

    }

}
