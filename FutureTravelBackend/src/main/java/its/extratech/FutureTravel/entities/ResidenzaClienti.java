package its.extratech.FutureTravel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RESIDENZA_CLIENTI")
public class ResidenzaClienti {

    @Id
    @Column(name = "ID")
    public String id;

    @Column(name = "DESCRIZIONE")
    public String descrizione;

    @OneToMany(mappedBy = "residenzaClienti", fetch = FetchType.LAZY)
    public Set<Contesto> contestoList;
}
