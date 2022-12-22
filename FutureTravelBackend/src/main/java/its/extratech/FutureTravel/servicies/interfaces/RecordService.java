package its.extratech.FutureTravel.servicies.interfaces;

import its.extratech.FutureTravel.dtos.RecordFrontEndDto;
import its.extratech.FutureTravel.entities.Record;

import java.util.List;

public interface RecordService {

    void save(Record record);

    String fetch();

    List<RecordFrontEndDto> findByNomeProvincia(String nomeProvincia);

    RecordFrontEndDto fromRecordToRecordFrontEndDto(Record record);
}
