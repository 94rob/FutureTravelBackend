package its.extratech.FutureTravel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="TIPO_ALLOGGIO")
public class TipoAlloggio {

    @Id
    @Column(name = "ID_ALLOGGIO")
    public String id;

    @Column(name = "DESCRIZIONE")
    public String descrizione;

    @OneToMany(mappedBy = "tipoAlloggio", fetch = FetchType.LAZY)
    public List<Contesto> contestoList;
}
