package its.extratech.FutureTravel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="PROVINCIA")
public class Provincia {

    @Id
    @Column (name = "ID")
    public String id;

    @Column(name = "NOME_PROVINCIA")
    public String nomeProvincia;

    @OneToMany(mappedBy = "provincia", fetch = FetchType.LAZY)
    private List<Contesto> contesti;

}
