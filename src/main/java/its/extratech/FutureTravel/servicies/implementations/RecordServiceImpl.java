package its.extratech.FutureTravel.servicies.implementations;

import its.extratech.FutureTravel.dtos.RecordDtoCompleto;
import its.extratech.FutureTravel.dtos.RecordDtoPresenze;
import its.extratech.FutureTravel.entities.*;
import its.extratech.FutureTravel.entities.Record;
import its.extratech.FutureTravel.importData.XMLIstatReader;
import its.extratech.FutureTravel.repositories.RecordRepository;
import its.extratech.FutureTravel.servicies.interfaces.RecordService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    ContestoServiceImpl contestoServiceImpl;

    @Autowired
    ProvinciaServiceImpl provinciaServiceImpl;

    @Autowired
    TipoAlloggioServiceImpl tipoAlloggioServiceImpl;

    @Autowired
    ResidenzaClientiServiceImpl residenzaClientiServiceImpl;

    @Autowired
    ModelMapper modelMapper;

    public void save(Record record){
        this.recordRepository.save(record);
    }


    public List<RecordDtoPresenze> findAll(){
        return this.recordRepository.findAll()
                .stream()
                .map(this::fromRecordToRecordDtoPresenze)
                .collect(Collectors.toList());
    }

    public List<RecordDtoPresenze> findByTime(String time){
        return this.recordRepository.findByTime(time)
                .stream()
                .map(this::fromRecordToRecordDtoPresenze)
                .collect(Collectors.toList());
    }

    public List<RecordDtoPresenze> selByIdProvincia(String idProvincia){
        return  this.recordRepository.selByIdProvincia(idProvincia)
                .stream()
                .map(this::fromRecordToRecordDtoPresenze)
                .collect(Collectors.toList());
    }

    public List<RecordDtoCompleto> selByNomeProvincia(String nomeProvincia){
        return this.recordRepository.selByNomeProvincia(nomeProvincia)
                .stream()
                .map(this::fromRecordToRecordDtoCompleto)
                .collect(Collectors.toList());
    }


    public List<RecordDtoPresenze> selByIdTipoAlloggio(String idAlloggio){
        return this.recordRepository.selByIdTipoAlloggio(idAlloggio)
                .stream()
                .map(this::fromRecordToRecordDtoPresenze)
                .collect(Collectors.toList());
    }

    public List<RecordDtoPresenze> selByIdResidenzaClienti(String idResidenzaClienti){
        return this.recordRepository.selByIdResidenzaClienti(idResidenzaClienti)
                .stream()
                .map(this::fromRecordToRecordDtoPresenze)
                .collect(Collectors.toList());
    }


    public RecordDtoCompleto fromRecordToRecordDtoCompleto(Record record){
        return modelMapper.map(record, RecordDtoCompleto.class);
    }

    public RecordDtoPresenze fromRecordToRecordDtoPresenze(Record record){
        return modelMapper.map(record, RecordDtoPresenze.class);
    }


    public void fetch() throws IOException {
        XMLIstatReader xmlIstatReader = new XMLIstatReader();
        List<Record> recordList = xmlIstatReader.fetch();

        for (Record r : recordList){
            Provincia provincia = this.provinciaServiceImpl
                    .findById(r.getContesto().getProvincia().getId());
            TipoAlloggio tipoAlloggio = this.tipoAlloggioServiceImpl
                    .findById(r.getContesto().getTipoAlloggio().getId());
            ResidenzaClienti residenzaClienti = this.residenzaClientiServiceImpl
                    .findById(r.getContesto().getResidenzaClienti().getId());
            Contesto contesto = this.contestoServiceImpl
                    .findByProvinciaAndTipoAlloggioAndResidenzaClienti(provincia, tipoAlloggio, residenzaClienti);

            r.setContesto(contesto);
            this.save(r);
        }
    }
}
