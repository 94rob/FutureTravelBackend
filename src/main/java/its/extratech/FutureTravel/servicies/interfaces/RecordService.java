package its.extratech.FutureTravel.servicies.interfaces;

import its.extratech.FutureTravel.dtos.RecordDtoCompleto;
import its.extratech.FutureTravel.dtos.RecordDtoPresenze;
import its.extratech.FutureTravel.entities.Contesto;
import its.extratech.FutureTravel.entities.Record;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface RecordService {

    List<RecordDtoCompleto> selByIdTipoAlloggioFE(String idAlloggio);

    List<RecordDtoCompleto> selByIdTipoAlloggioFE(String idAlloggio, String startDate);

    List<RecordDtoCompleto> selByIdTipoAlloggioFE(String idAlloggio, String startDate, String endDate);

    List<RecordDtoCompleto> selByIdTipoAlloggioAndByIdProvinciaFE(String idAlloggio, String idProvincia);

    List<RecordDtoCompleto> selByIdTipoAlloggioAndByIdProvinciaFE(String idAlloggio, String startDate, String idProvincia);

    List<RecordDtoCompleto> selByIdTipoAlloggioAndByIdProvinciaFE(String idAlloggio, String startDate, String endDate, String idProvincia);

    List<RecordDtoCompleto> selByIdResidenzaClientiFE(String idResidenza);

    List<RecordDtoCompleto> selByIdResidenzaClientiFE(String idResidenza, String startDate);

    List<RecordDtoCompleto> selByIdResidenzaClientiFE(String idResidenza, String startDate, String endDate);

    List<RecordDtoCompleto> selByIdResidenzaClientiAndByIdProvinciaFE(String idResidenza, String idProvincia);

    List<RecordDtoCompleto> selByIdResidenzaClientiAndByIdProvinciaFE(String idResidenza, String startDate, String idProvincia);

    List<RecordDtoCompleto> selByIdResidenzaClientiAndByIdProvinciaFE(String idResidenza, String startDate, String endDate, String idProvincia);

    List<RecordDtoCompleto> selByIdTipoAlloggioAbsFE(String idAlloggio);

    List<RecordDtoCompleto> selByIdTipoAlloggioAbsFE(String idAlloggio, String startDate);

    List<RecordDtoCompleto> selByIdTipoAlloggioAbsFE(String idAlloggio, String startDate, String endDate);

    List<RecordDtoCompleto> selByIdTipoAlloggioAndByIdProvinciaAbsFE(String idAlloggio, String idProvincia);

    List<RecordDtoCompleto> selByIdTipoAlloggioAndByIdProvinciaAbsFE(String idAlloggio, String startDate, String idProvincia);

    List<RecordDtoCompleto> selByIdTipoAlloggioAndByIdProvinciaAbsFE(String idAlloggio, String startDate, String endDate, String idProvincia);

    List<RecordDtoCompleto> selByIdResidenzaClientiAbsFE(String idResidenza);

    List<RecordDtoCompleto> selByIdResidenzaClientiAbsFE(String idResidenza, String startDate);

    List<RecordDtoCompleto> selByIdResidenzaClientiAbsFE(String idResidenza, String startDate, String endDate);

    List<RecordDtoCompleto> selByIdResidenzaClientiAndByIdProvinciaAbsFE(String idResidenza, String idProvincia);

    List<RecordDtoCompleto> selByIdResidenzaClientiAndByIdProvinciaAbsFE(String idResidenza, String startDate, String idProvincia);

    List<RecordDtoCompleto> selByIdResidenzaClientiAndByIdProvinciaAbsFE(String idResidenza, String startDate, String endDate, String idProvincia);

    List<RecordDtoCompleto> selAbsoluteData();

    List<RecordDtoCompleto> selAbsoluteData(String startDate);

    List<RecordDtoCompleto> selAbsoluteData(String startDate, String endDate);

    List<RecordDtoCompleto> selAbsoluteDataByIdProvincia(String idProvincia);

    List<RecordDtoCompleto> selAbsoluteDataByIdProvincia(String startDate, String idProvincia);

    List<RecordDtoCompleto> selAbsoluteDataByIdProvincia(String startDate, String endDate, String idProvincia);

    void save(Record record);

    List<RecordDtoPresenze> findAll();

    List<RecordDtoPresenze> findByTime(String time);

    List<RecordDtoPresenze> selByIdProvincia(String idProvincia);

    List<RecordDtoCompleto> selByNomeProvincia(String nomeProvincia);

    List<RecordDtoPresenze> selByIdTipoAlloggio(String idAlloggio);

    List<RecordDtoPresenze> selByIdResidenzaClienti(String idResidenzaClienti);

    RecordDtoCompleto fromRecordToRecordDtoCompleto(Record record);

    RecordDtoPresenze fromRecordToRecordDtoPresenze(Record record);

    void fetch() throws IOException, InterruptedException;

    List<Record> collapseResidenzeClienti(List<Record> recordList);

    List<Record> collapseTipoAlloggio(List<Record> recordList);
}
