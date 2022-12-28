package its.extratech.FutureTravel.repositories;

import its.extratech.FutureTravel.entities.TipoAlloggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoAlloggioRepository extends JpaRepository<TipoAlloggio, String> {
}
