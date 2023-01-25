package its.extratech.FutureTravel.servicies.interfaces;

import its.extratech.FutureTravel.dtos.request.FintechRequest;
import java.util.List;

public interface IFintechService{

     void deleteOldPrevisionRecords(char tipoDato);

     boolean saveUpload (FintechRequest request);

     boolean saveUploadList(List<FintechRequest> request);

}
