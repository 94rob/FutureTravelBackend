package its.extratech.FutureTravel.servicies;

import its.extratech.FutureTravel.entities.Record;
import its.extratech.FutureTravel.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordService {

    @Autowired
    RecordRepository recordRepository;

    public void save(Record record){
        this.recordRepository.save(record);
    }
}
