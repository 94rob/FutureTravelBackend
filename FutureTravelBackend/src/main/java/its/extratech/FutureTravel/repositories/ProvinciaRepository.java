package its.extratech.FutureTravel.repositories;

import its.extratech.FutureTravel.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, String> {

    Provincia findByNomeProvincia(String nomeProvincia);

    @Query(value = "select * From PROVINCIA" ,
            nativeQuery = true)
    List<Provincia> FindAllProvicia();
}
