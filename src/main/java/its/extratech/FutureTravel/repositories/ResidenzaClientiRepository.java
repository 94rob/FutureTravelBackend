package its.extratech.FutureTravel.repositories;

import its.extratech.FutureTravel.entities.Provincia;
import its.extratech.FutureTravel.entities.ResidenzaClienti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidenzaClientiRepository extends JpaRepository<ResidenzaClienti, String> {

    //ResidenzaClienti findByNome(String nomeProvincia);

}
