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
            "INNER JOIN TIPO_ALLOGGIO ta ON c.TIPO_ALLOGGIO = ta.ID_ALLOGGIO " +
            "INNER JOIN PROVINCIA p ON c.PROVINCIA = p.ID_PROVINCIA " +
            "WHERE ta.ID_ALLOGGIO LIKE :idAlloggio " +
            "AND p.ID_PROVINCIA LIKE :idProvincia ;", nativeQuery = true)
    List<Record> selByIdTipoAlloggioAndByIdProvincia(@Param("idAlloggio") String idAlloggio,
                                                     @Param("idProvincia") String idProvincia);


    @Query(value = "SELECT * FROM STORICO s " +
            "INNER JOIN CONTESTO c ON s.CONTESTO = c.ID_CONTESTO " +
            "INNER JOIN TIPO_ALLOGGIO ta ON c.TIPO_ALLOGGIO = ta.ID_ALLOGGIO " +
            "WHERE ta.ID_ALLOGGIO LIKE :idAlloggio " +
            "AND s.TIME >= :startDate " +
            "ORDER BY s.TIME ASC;", nativeQuery = true)
    List<Record> selByIdTipoAlloggioSinceDate(@Param("idAlloggio") String idAlloggio,
                                              @Param("startDate") String startDate);

    @Query(value = "SELECT * FROM STORICO s " +
            "INNER JOIN CONTESTO c ON s.CONTESTO = c.ID_CONTESTO " +
            "INNER JOIN TIPO_ALLOGGIO ta ON c.TIPO_ALLOGGIO = ta.ID_ALLOGGIO " +
            "INNER JOIN PROVINCIA p ON c.PROVINCIA = p.ID_PROVINCIA " +
            "WHERE ta.ID_ALLOGGIO LIKE :idAlloggio " +
            "AND p.ID_PROVINCIA LIKE :idProvincia " +
            "AND s.TIME >= :startDate " +
            "ORDER BY s.TIME ASC;", nativeQuery = true)
    List<Record> selByIdTipoAlloggioAndByIdProvinciaSinceDate(@Param("idAlloggio") String idAlloggio,
                                                              @Param("startDate") String startDate,
                                                              @Param("idProvincia") String idProvincia);

    @Query(value = "SELECT * FROM STORICO s " +
            "INNER JOIN CONTESTO c ON s.CONTESTO = c.ID_CONTESTO " +
            "INNER JOIN TIPO_ALLOGGIO ta ON c.TIPO_ALLOGGIO = ta.ID_ALLOGGIO " +
            "WHERE ta.ID_ALLOGGIO LIKE :idAlloggio " +
            "AND s.TIME BETWEEN :startDate AND :endDate " +
            "ORDER BY s.TIME ASC;", nativeQuery = true)
    List<Record> selByIdTipoAlloggioBetweenTwoDates(@Param("idAlloggio") String idAlloggio,
                                                    @Param("startDate") String startDate,
                                                    @Param("endDate") String endDate);

    @Query(value = "SELECT * FROM STORICO s " +
            "INNER JOIN CONTESTO c ON s.CONTESTO = c.ID_CONTESTO " +
            "INNER JOIN TIPO_ALLOGGIO ta ON c.TIPO_ALLOGGIO = ta.ID_ALLOGGIO " +
            "INNER JOIN PROVINCIA p ON c.PROVINCIA = p.ID_PROVINCIA " +
            "WHERE ta.ID_ALLOGGIO LIKE :idAlloggio " +
            "AND p.ID_PROVINCIA LIKE :idProvincia " +
            "AND s.TIME BETWEEN :startDate AND :endDate " +
            "ORDER BY s.TIME ASC;", nativeQuery = true)
    List<Record> selByIdTipoAlloggioAndByIdProvinciaBetweenTwoDates(@Param("idAlloggio") String idAlloggio,
                                                                    @Param("startDate") String startDate,
                                                                    @Param("endDate") String endDate,
                                                                    @Param("idProvincia") String idProvincia);

    @Query(value = "SELECT * FROM STORICO s " +
            "INNER JOIN CONTESTO c ON s.CONTESTO = c.ID_CONTESTO " +
            "INNER JOIN RESIDENZA_CLIENTI rc ON c.RESIDENZA_CLIENTI = rc.ID_RESIDENZA " +
            "WHERE rc.ID_RESIDENZA LIKE :idResidenza ;", nativeQuery = true)
    List<Record> selByIdResidenzaClienti(@Param("idResidenza") String idResidenzaClienti);

    @Query(value = "SELECT * FROM STORICO s " +
            "INNER JOIN CONTESTO c ON s.CONTESTO = c.ID_CONTESTO " +
            "INNER JOIN RESIDENZA_CLIENTI rc ON c.RESIDENZA_CLIENTI = rc.ID_RESIDENZA " +
            "INNER JOIN PROVINCIA p ON c.PROVINCIA = p.ID_PROVINCIA " +
            "WHERE rc.ID_RESIDENZA LIKE :idResidenza " +
            "AND p.ID_PROVINCIA LIKE :idProvincia ;", nativeQuery = true)
    List<Record> selByIdResidenzaClientiAndByIdProvincia(@Param("idResidenza") String idResidenzaClienti,
                                                         @Param("idProvincia") String idProvincia);

    @Query(value = "SELECT * FROM STORICO s " +
            "INNER JOIN CONTESTO c ON s.CONTESTO = c.ID_CONTESTO " +
            "INNER JOIN RESIDENZA_CLIENTI rc ON c.RESIDENZA_CLIENTI = rc.ID_RESIDENZA " +
            "WHERE rc.ID_RESIDENZA LIKE :idResidenza " +
            "AND s.TIME >= :startDate " +
            "ORDER BY s.TIME ASC;", nativeQuery = true)
    List<Record> selByIdResidenzaClientiSinceDate(@Param("idResidenza") String idResidenzaClienti,
                                                  @Param("startDate") String startDate);

    @Query(value = "SELECT * FROM STORICO s " +
            "INNER JOIN CONTESTO c ON s.CONTESTO = c.ID_CONTESTO " +
            "INNER JOIN RESIDENZA_CLIENTI rc ON c.RESIDENZA_CLIENTI = rc.ID_RESIDENZA " +
            "INNER JOIN PROVINCIA p ON c.PROVINCIA = p.ID_PROVINCIA " +
            "WHERE rc.ID_RESIDENZA LIKE :idResidenza " +
            "AND p.ID_PROVINCIA LIKE :idProvincia " +
            "AND s.TIME >= :startDate " +
            "ORDER BY s.TIME ASC;", nativeQuery = true)
    List<Record> selByIdResidenzaClientiAndByIdProvinciaSinceDate(@Param("idResidenza") String idResidenzaClienti,
                                                                  @Param("startDate") String startDate,
                                                                  @Param("idProvincia") String idProvincia);

    @Query(value = "SELECT * FROM STORICO s " +
            "INNER JOIN CONTESTO c ON s.CONTESTO = c.ID_CONTESTO " +
            "INNER JOIN RESIDENZA_CLIENTI rc ON c.RESIDENZA_CLIENTI = rc.ID_RESIDENZA " +
            "WHERE rc.ID_RESIDENZA LIKE :idResidenza " +
            "AND s.TIME BETWEEN :startDate AND :endDate " +
            "ORDER BY s.TIME ASC;", nativeQuery = true)
    List<Record> selByIdResidenzaClientiBetweenTwoDates(@Param("idResidenza") String idResidenzaClienti,
                                                        @Param("startDate") String startDate,
                                                        @Param("endDate") String endDate);

    @Query(value = "SELECT * FROM STORICO s " +
            "INNER JOIN CONTESTO c ON s.CONTESTO = c.ID_CONTESTO " +
            "INNER JOIN RESIDENZA_CLIENTI rc ON c.RESIDENZA_CLIENTI = rc.ID_RESIDENZA " +
            "INNER JOIN PROVINCIA p ON c.PROVINCIA = p.ID_PROVINCIA " +
            "WHERE rc.ID_RESIDENZA LIKE :idResidenza " +
            "AND p.ID_PROVINCIA LIKE :idProvincia " +
            "AND s.TIME BETWEEN :startDate AND :endDate " +
            "ORDER BY s.TIME ASC;", nativeQuery = true)
    List<Record> selByIdResidenzaClientiAndByIdProvinciaBetweenTwoDates(@Param("idResidenza") String idResidenzaClienti,
                                                                        @Param("startDate") String startDate,
                                                                        @Param("endDate") String endDate,
                                                                        @Param("idProvincia") String idProvincia);

    @Query(value = "SELECT * FROM STORICO s " +
            "INNER JOIN CONTESTO c ON s.CONTESTO = c.ID_CONTESTO " +
            "INNER JOIN PROVINCIA p ON c.PROVINCIA = p.ID_PROVINCIA " +
            "WHERE p.ID_PROVINCIA LIKE :idProvincia ;", nativeQuery = true)
    List<Record> selByIdProvincia(@Param("idProvincia") String idProvincia);

    List<Record> findByContesto(Contesto contesto);

    List<Record> findByTime(String time);

}
