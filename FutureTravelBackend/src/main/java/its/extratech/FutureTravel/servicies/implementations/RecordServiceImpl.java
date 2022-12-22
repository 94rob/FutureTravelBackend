package its.extratech.FutureTravel.servicies.implementations;

import its.extratech.FutureTravel.dtos.RecordFrontEndDto;
import its.extratech.FutureTravel.entities.*;
import its.extratech.FutureTravel.entities.Record;
import its.extratech.FutureTravel.importData.XMLIstatReader;
import its.extratech.FutureTravel.repositories.RecordRepository;
import its.extratech.FutureTravel.servicies.interfaces.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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


    public void save(Record record){
        this.recordRepository.save(record);
    }

    public String fetch(){
        XMLIstatReader xmlIstatReader = new XMLIstatReader();
        List<Record> recordList = null;
        try {
            recordList = xmlIstatReader.fetch();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Record r : recordList){

            Provincia provincia = this.provinciaServiceImpl.findById(r.getContesto().getProvincia().getId());
            TipoAlloggio tipoAlloggio = this.tipoAlloggioServiceImpl.findById(r.getContesto().getTipoAlloggio().getId());
            ResidenzaClienti residenzaClienti = this.residenzaClientiServiceImpl.findById(r.getContesto().getResidenzaClienti().getId());
            Contesto contesto;
            try{
                contesto = this.contestoServiceImpl.findByProvinciaAndTipoAlloggioAndResidenzaClienti(provincia, tipoAlloggio, residenzaClienti);
            } catch (NullPointerException e){
                contesto = new Contesto();
                contesto.setProvincia(provincia);
                contesto.setTipoAlloggio(tipoAlloggio);
                contesto.setResidenzaClienti(residenzaClienti);
                this.contestoServiceImpl.save(contesto);
            } finally {
                contesto = this.contestoServiceImpl.findByProvinciaAndTipoAlloggioAndResidenzaClienti(provincia, tipoAlloggio, residenzaClienti);
            }

            r.setContesto(contesto);
            this.save(r);
        }

        return recordList.get(0).toString();
    }

    public List<RecordFrontEndDto> findByNomeProvincia(String nomeProvincia){
        List<Record> recordList = this.recordRepository.selByProvincia(nomeProvincia);
        List<RecordFrontEndDto> recordFrontEndDtoList = new ArrayList<>();
        try{
            for(Record r : recordList){
                recordFrontEndDtoList.add(this.fromRecordToRecordFrontEndDto(r));
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        return recordFrontEndDtoList;
    }

    public RecordFrontEndDto fromRecordToRecordFrontEndDto(Record record){
        RecordFrontEndDto recordFrontEndDto = new RecordFrontEndDto();

        recordFrontEndDto.setTerritorio(record.getContesto().getProvincia().getNomeProvincia());
        recordFrontEndDto.setResidenza_clienti(record.getContesto().getResidenzaClienti().getDescrizione());
        recordFrontEndDto.setTipologia_esercizio(record.getContesto().getTipoAlloggio().getDescrizione());
        recordFrontEndDto.setTime(record.getTime());
        recordFrontEndDto.setArrivi(record.getArrivi());
        recordFrontEndDto.setPresenze(record.getPresenze());

        return recordFrontEndDto;
    }
}
