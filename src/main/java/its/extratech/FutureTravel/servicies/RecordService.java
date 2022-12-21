package its.extratech.FutureTravel.servicies;

import its.extratech.FutureTravel.entities.*;
import its.extratech.FutureTravel.entities.Record;
import its.extratech.FutureTravel.importData.XMLIstatReader;
import its.extratech.FutureTravel.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
        List<Record> recordList = null;
        try {
            recordList = xmlIstatReader.fetch();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Record r : recordList){

            Provincia provincia = this.provinciaService.findById(r.getContesto().getProvincia().getId());
            TipoAlloggio tipoAlloggio = this.tipoAlloggioService.findById(r.getContesto().getTipoAlloggio().getId());
            ResidenzaClienti residenzaClienti = this.residenzaClientiService.findById(r.getContesto().getResidenzaClienti().getId());
            Contesto contesto;
            try{
                contesto = this.contestoService.findByProvinciaAndTipoAlloggioAndResidenzaClienti(provincia, tipoAlloggio, residenzaClienti);
            } catch (NullPointerException e){
                contesto = new Contesto();
                contesto.setProvincia(provincia);
                contesto.setTipoAlloggio(tipoAlloggio);
                contesto.setResidenzaClienti(residenzaClienti);
                this.contestoService.save(contesto);
            } finally {
                contesto = this.contestoService.findByProvinciaAndTipoAlloggioAndResidenzaClienti(provincia, tipoAlloggio, residenzaClienti);
            }

            r.setContesto(contesto);
            this.save(r);
        }

        return recordList.get(0).toString();

    }
}
