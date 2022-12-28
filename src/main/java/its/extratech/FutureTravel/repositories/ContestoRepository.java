package its.extratech.FutureTravel.repositories;

import its.extratech.FutureTravel.entities.Contesto;
import its.extratech.FutureTravel.entities.Provincia;
import its.extratech.FutureTravel.entities.ResidenzaClienti;
import its.extratech.FutureTravel.entities.TipoAlloggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContestoRepository extends JpaRepository<Contesto, Long> {

    List<Contesto> findByProvinciaAndTipoAlloggioAndResidenzaClienti(Provincia provincia, TipoAlloggio tipoAlloggio, ResidenzaClienti residenzaClienti);

    List<Contesto> findByTipoAlloggio(TipoAlloggio tipoAlloggio);
}
