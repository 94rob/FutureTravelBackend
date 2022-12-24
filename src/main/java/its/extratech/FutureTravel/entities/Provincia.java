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
@Table(name="PROVINCIA")
public class Provincia {

    @Id
    @Column (name = "ID_PROVINCIA")
    public String id;

    @Column(name = "NOME_PROVINCIA")
    public String nomeProvincia;

    @OneToMany(mappedBy = "provincia", fetch = FetchType.LAZY)
    private List<Contesto> contesti;

}
