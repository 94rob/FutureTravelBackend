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
    @Column(name = "ID_STORICO")
    public long id;

    @JoinColumn (name = "CONTESTO", referencedColumnName = "ID_CONTESTO")
    @ManyToOne
    public Contesto contesto;

    @Column(name = "TIME")
    public String time;

    @Column(name = "PRESENZE")
    public int presenze;

    @Column(name = "ARRIVI")
    public int arrivi;

    @Override
    public String toString(){
        return "Contesto: " +
                "   \n\tId provincia: " + this.contesto.getProvincia().getId() +
                "   \n\tId tipo alloggio: " + this.contesto.getTipoAlloggio().getId() +
                "   \n\tId residenza clienti: " + this.contesto.getResidenzaClienti().getId() +
                "\n Mese: " + this.getTime() +
                "\n Presenze: " + this.getPresenze() +
                "\n Arrivi: " + this.getArrivi();
    }
}
