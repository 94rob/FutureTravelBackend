package its.extratech.FutureTravel.repositories;

import its.extratech.FutureTravel.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, String> {
}
