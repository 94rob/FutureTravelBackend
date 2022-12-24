package its.extratech.FutureTravel.repositories;

import its.extratech.FutureTravel.entities.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

    @Query(value = "SELECT * FROM storico " +
            "INNER JOIN contesto ON storico.CONTESTO = contesto.ID_CONTESTO " +
            "INNER JOIN provincia ON contesto.TERRITORIO = provincia.ID_PROVINCIA " +
            "WHERE provincia.NOME_PROVINCIA LIKE :nomeProvincia ;", nativeQuery = true)
    List<Record> selByProvincia(@Param("nomeProvincia") String nomeProvincia);
}
