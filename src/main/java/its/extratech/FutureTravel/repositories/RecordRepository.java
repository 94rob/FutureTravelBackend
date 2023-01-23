package its.extratech.FutureTravel.repositories;

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
            "WHERE p.ID_PROVINCIA LIKE :idProvincia " +
            "ORDER BY s.TIME ASC ;", nativeQuery = true)
    List<Record> selByIdProvincia(@Param("idProvincia") String idProvincia);

    @Query(value = "SELECT * FROM STORICO s " +
            "INNER JOIN CONTESTO c ON s.CONTESTO = c.ID_CONTESTO " +
            "INNER JOIN PROVINCIA p ON c.PROVINCIA = p.ID_PROVINCIA " +
            "WHERE p.NOME_PROVINCIA LIKE :nomeProvincia " +
            "ORDER BY s.TIME ASC ;", nativeQuery = true)
    List<Record> selByNomeProvincia(@Param("nomeProvincia") String nomeProvincia);

    @Query(value = "SELECT * FROM STORICO s " +
            "INNER JOIN CONTESTO c ON s.CONTESTO = c.ID_CONTESTO " +
            "INNER JOIN TIPO_ALLOGGIO ta ON c.TIPO_ALLOGGIO = ta.ID_ALLOGGIO " +
            "WHERE ta.ID_ALLOGGIO LIKE :idAlloggio " +
            "ORDER BY s.TIME ASC ;", nativeQuery = true)
    List<Record> selByIdTipoAlloggio(@Param("idAlloggio") String idAlloggio);

    @Query(value = "SELECT * FROM STORICO s " +
            "INNER JOIN CONTESTO c ON s.CONTESTO = c.ID_CONTESTO " +
            "INNER JOIN TIPO_ALLOGGIO ta ON c.TIPO_ALLOGGIO = ta.ID_ALLOGGIO " +
            "INNER JOIN PROVINCIA p ON c.PROVINCIA = p.ID_PROVINCIA " +
            "WHERE ta.ID_ALLOGGIO LIKE :idAlloggio " +
            "AND p.ID_PROVINCIA LIKE :idProvincia " +
            "ORDER BY s.TIME ASC ;", nativeQuery = true)
    List<Record> selByIdTipoAlloggioAndByIdProvincia(@Param("idAlloggio") String idAlloggio,
                                                     @Param("idProvincia") String idProvincia);

    @Query(value = "SELECT * FROM STORICO s " +
            "INNER JOIN CONTESTO c ON s.CONTESTO = c.ID_CONTESTO " +
            "INNER JOIN RESIDENZA_CLIENTI rc ON c.RESIDENZA_CLIENTI = rc.ID_RESIDENZA " +
            "WHERE rc.ID_RESIDENZA LIKE :idResidenza " +
            "ORDER BY s.TIME ASC ;", nativeQuery = true)
    List<Record> selByIdResidenzaClienti(@Param("idResidenza") String idResidenzaClienti);

    @Query(value = "SELECT * FROM STORICO s " +
            "INNER JOIN CONTESTO c ON s.CONTESTO = c.ID_CONTESTO " +
            "INNER JOIN RESIDENZA_CLIENTI rc ON c.RESIDENZA_CLIENTI = rc.ID_RESIDENZA " +
            "INNER JOIN PROVINCIA p ON c.PROVINCIA = p.ID_PROVINCIA " +
            "WHERE rc.ID_RESIDENZA LIKE :idResidenza " +
            "AND p.ID_PROVINCIA LIKE :idProvincia " +
            "ORDER BY s.TIME ASC ;", nativeQuery = true)
    List<Record> selByIdResidenzaClientiAndByIdProvincia(@Param("idResidenza") String idResidenzaClienti,
                                                         @Param("idProvincia") String idProvincia);

    @Query(value = "SELECT * FROM STORICO s " +
            "INNER JOIN CONTESTO c ON s.CONTESTO = c.ID_CONTESTO " +
            "INNER JOIN RESIDENZA_CLIENTI rc ON c.RESIDENZA_CLIENTI = rc.ID_RESIDENZA " +
            "INNER JOIN TIPO_ALLOGGIO ta ON c.TIPO_ALLOGGIO = ta.ID_ALLOGGIO " +
            "WHERE rc.ID_RESIDENZA LIKE :idResidenza " +
            "AND ta.ID_ALLOGGIO LIKE :idAlloggio " +
            "ORDER BY s.TIME ASC ;", nativeQuery = true)
    List<Record> selByIdResidenzaAndIdAlloggio(@Param("idResidenza") String idResidenzaClienti,
                                                @Param("idAlloggio") String idTipoAlloggio);

    @Query(value = "SELECT * FROM STORICO s " +
            "INNER JOIN CONTESTO c ON s.CONTESTO = c.ID_CONTESTO " +
            "INNER JOIN RESIDENZA_CLIENTI rc ON c.RESIDENZA_CLIENTI = rc.ID_RESIDENZA " +
            "INNER JOIN TIPO_ALLOGGIO ta ON c.TIPO_ALLOGGIO = ta.ID_ALLOGGIO " +
            "INNER JOIN PROVINCIA p ON c.PROVINCIA = p.ID_PROVINCIA " +
            "WHERE rc.ID_RESIDENZA LIKE :idResidenza " +
            "AND ta.ID_ALLOGGIO LIKE :idAlloggio " +
            "AND p.ID_PROVINCIA LIKE :idProvincia " +
            "ORDER BY s.TIME ASC ;", nativeQuery = true)
    List<Record> selByIdResidenzaAndIdAlloggioByProvincia(@Param("idResidenza") String idResidenzaClienti,
                                                          @Param("idAlloggio") String idTipoAlloggio,
                                                          @Param("idProvincia") String idProvincia);
    @Query(value = "SELECT * FROM STORICO s " +
            "ORDER BY s.TIME ASC; ", nativeQuery = true)
    List<Record> selAllOrderedByTimeAsc();

    @Query(value = "SELECT * FROM STORICO s " +
            "WHERE TIPODATO LIKE 'S' " +
            "ORDER BY s.TIME DESC; ", nativeQuery = true)
    List<Record> selAllOrderedByTimeDescWhereTipodatoLikeS();

    List<Record> findByTime(String time);

    void deleteByTipoDato(char tipoDato);


}
