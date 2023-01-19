package its.extratech.FutureTravel.servicies.interfaces;

import its.extratech.FutureTravel.dtos.response.RecordDtoCompleto;
import its.extratech.FutureTravel.dtos.response.RecordDtoPresenze;
import its.extratech.FutureTravel.entities.Record;
import java.io.IOException;
import java.util.List;

public interface RecordService {

    List<RecordDtoCompleto> selByIdTipoAlloggioFE(String idAlloggio);

    List<RecordDtoCompleto> selByIdTipoAlloggioFE(String idAlloggio, String startDate);

    List<RecordDtoCompleto> selByIdTipoAlloggioFE(String idAlloggio, String startDate, String endDate);

    List<RecordDtoCompleto> selByIdTipoAlloggioAndByIdProvinciaFE(String idAlloggio, String idProvincia);

    List<RecordDtoCompleto> selByIdTipoAlloggioAndByIdProvinciaFE(String idAlloggio, String startDate, String idProvincia);

    List<RecordDtoCompleto> selByIdTipoAlloggioAndByIdProvinciaFE(String idAlloggio, String startDate, String endDate, String idProvincia);

    List<RecordDtoCompleto> selByIdResidenzaClientiFE(String idResidenza);

    List<RecordDtoCompleto> selByIdResidenzaClienti(String idResidenza, String startDate);

    List<RecordDtoCompleto> selByIdResidenzaClienti(String idResidenza, String startDate, String endDate);

    List<RecordDtoCompleto> selByIdResidenzaClientiAndByIdProvincia(String idResidenza, String idProvincia);

    List<RecordDtoCompleto> selByIdResidenzaClientiAndByIdProvincia(String idResidenza, String startDate, String idProvincia);

    List<RecordDtoCompleto> selByIdResidenzaClientiAndByIdProvincia(String idResidenza, String startDate, String endDate, String idProvincia);

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
