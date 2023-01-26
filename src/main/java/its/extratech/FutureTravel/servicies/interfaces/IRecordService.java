package its.extratech.FutureTravel.servicies.interfaces;

import its.extratech.FutureTravel.dtos.response.RecordDto;
import its.extratech.FutureTravel.entities.Record;
import java.io.IOException;
import java.util.List;

public interface IRecordService {

    // Metodi di ricerca dati

    List<RecordDto> getByResidenzaAndAlloggio(char tipoDato, String idResidenza, String idAlloggio, String startDate, String endDate);

    List<RecordDto> getByResidenzaAndAlloggioByProvincia(char tipoDato, String idResidenza, String idAlloggio, String idProvincia, String startDate, String endDate);

    List<RecordDto> getAllByTipoDato(char tipoDato);

    String getLastDateRegistered();

    // Metodi di nserimento dati

    boolean fetchIstatApiAndSaveData(String date) throws IOException ;

    void save(Record record);

}
