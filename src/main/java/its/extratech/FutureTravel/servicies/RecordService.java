package its.extratech.FutureTravel.servicies;

import its.extratech.FutureTravel.entities.Record;
import its.extratech.FutureTravel.importData.XMLIstatReader;
import its.extratech.FutureTravel.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    ContestoService contestoService;

    @Autowired
    ProvinciaService provinciaService;

    @Autowired
    TipoAlloggioService tipoAlloggioService;

    @Autowired
    ResidenzaClientiService residenzaClientiService;


    public void save(Record record){
        this.recordRepository.save(record);
    }

    public String fetch(){
        XMLIstatReader xmlIstatReader = new XMLIstatReader();
        List<Record> recordList = xmlIstatReader.fetch();

        return recordList.get(0).toString();

        /*
        int i = 0;
        for(Record r : recordList){

            contestoService.save(r.getContesto());

            r.setContesto(contestoService
                    .findByProvinciaAndTipoAlloggioAndResidenzaClienti(this.provinciaService.findById(r.getContesto().getProvincia().getId()),
                                                                       this.tipoAlloggioService.findById(r.getContesto().getTipoAlloggio().getId()),
                                                                       this.residenzaClientiService.findById(r.getContesto().getResidenzaClienti().getId())));


            this.save(r);

            if(i%50 == 0){
                System.out.println("Salvati 50");
            }
            i++;


        }
        */

    }
}
