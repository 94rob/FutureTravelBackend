package its.extratech.FutureTravel.servicies.interfaces;

import its.extratech.FutureTravel.dtos.RecordDtoCompleto;
import its.extratech.FutureTravel.dtos.RecordDtoPresenze;
import its.extratech.FutureTravel.entities.Contesto;
import its.extratech.FutureTravel.entities.Record;
import java.io.IOException;
import java.util.List;

public interface RecordService {

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
}
