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
@Table(name = "STORICO")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public long id;

    @JoinColumn (name = "CONTESTO", referencedColumnName = "ID")
    @ManyToOne
    public Contesto contesto;

    @Column(name = "TIME")
    public String time;

    @Column(name = "PRESENZE")
    public int presenze;

    @Column(name = "ARRIVI")
    public int arrivi;
}
