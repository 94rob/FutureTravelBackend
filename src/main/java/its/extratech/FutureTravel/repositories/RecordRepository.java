package its.extratech.FutureTravel.repositories;

import its.extratech.FutureTravel.entities.Contesto;
import its.extratech.FutureTravel.entities.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

    @Query(value = "SELECT * FROM STORICO s " +
            "INNER JOIN CONTESTO c ON s.CONTESTO = c.ID_CONTESTO " +
            "INNER JOIN PROVINCIA p ON c.PROVINCIA = p.ID_PROVINCIA " +
            "WHERE p.NOME_PROVINCIA LIKE :nomeProvincia ;", nativeQuery = true)
    List<Record> selByNomeProvincia(@Param("nomeProvincia") String nomeProvincia);

    @Query(value = "SELECT * FROM STORICO s " +
            "INNER JOIN CONTESTO c ON s.CONTESTO = c.ID_CONTESTO " +
            "INNER JOIN TIPO_ALLOGGIO ta ON c.TIPO_ALLOGGIO = ta.ID_ALLOGGIO " +
            "WHERE ta.ID_ALLOGGIO LIKE :idAlloggio ;", nativeQuery = true)
    List<Record> selByIdTipoAlloggio(@Param("idAlloggio") String idAlloggio);

    @Query(value = "SELECT * FROM STORICO s " +
            "INNER JOIN CONTESTO c ON s.CONTESTO = c.ID_CONTESTO " +
            "INNER JOIN RESIDENZA_CLIENTI rc ON c.RESIDENZA_CLIENTI = rc.ID_RESIDENZA " +
            "WHERE rc.ID_RESIDENZA LIKE :idResidenza ;", nativeQuery = true)
    List<Record> selByIdResidenzaClienti(@Param("idResidenza") String idResidenzaClienti);

    @Query(value = "SELECT * FROM STORICO s " +
            "INNER JOIN CONTESTO c ON s.CONTESTO = c.ID_CONTESTO " +
            "INNER JOIN PROVINCIA p ON c.PROVINCIA = p.ID_PROVINCIA " +
            "WHERE p.ID_PROVINCIA LIKE :idProvincia ;", nativeQuery = true)
    List<Record> selByIdProvincia(@Param("idProvincia") String idProvincia);

    List<Record> findByContesto(Contesto contesto);

    List<Record> findByTime(String time);

}
