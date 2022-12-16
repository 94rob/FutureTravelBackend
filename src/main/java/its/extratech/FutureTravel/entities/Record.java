package its.extratech.FutureTravel.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "STORICO")
public class Record {

    @Id
    public long id;

    @Column(name = "CONTESTO")
    public Contesto contesto;

    @Column(name = "TIME")
    public int time;

    @Column(name = "PRESENZE")
    public int presenze;

    @Column(name = "ARRIVI")
    public int arrivi;
}
