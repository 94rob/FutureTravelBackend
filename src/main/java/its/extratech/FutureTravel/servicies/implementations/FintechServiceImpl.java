package its.extratech.FutureTravel.servicies.implementations;

import its.extratech.FutureTravel.dtos.request.FintechRequest;
import its.extratech.FutureTravel.entities.*;
import its.extratech.FutureTravel.entities.Record;
import its.extratech.FutureTravel.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.List;

@Service
public class FintechServiceImpl {

    @Autowired
    TipoAlloggioServiceImpl tipoAlloggioService;

    @Autowired
    ProvinciaServiceImpl provinciaService;

    @Autowired
    ResidenzaClientiServiceImpl residenzaClientiService;

    @Autowired
    ContestoServiceImpl contestoService;

    @Autowired
    RecordServiceImpl recordService;

    @Autowired
    RecordRepository recordRepository;

    @Transactional
    public void deleteOldPrevisionRecords(char tipoDato){
        this.recordRepository.deleteByTipoDato(tipoDato);
    }

    public void saveUpload (FintechRequest request)
    {
        Record record = new Record();
        // debug
        System.out.println(request.getProvincia()  + " - " + request.getTipoAlloggio() + " - " + request.getResidenzaClienti());

        Provincia provincia = this.provinciaService.findById(request.getProvincia());
        TipoAlloggio tipoAlloggio = this.tipoAlloggioService.findById(request.getTipoAlloggio());
        ResidenzaClienti residenzaClienti = this.residenzaClientiService.findById(request.getResidenzaClienti());

        Contesto contesto = this.contestoService.findByProvinciaAndTipoAlloggioAndResidenzaClienti(provincia, tipoAlloggio, residenzaClienti);

        record.setContesto(contesto);

        record.setTime(this.correctTime(request.getTime()));

        record.setPresenze(request.getValue());
        record.setTipoDato('P');

        recordService.save(record);
    }


    public void saveListUpload (List<FintechRequest> request)
    {
        for (FintechRequest f: request) {
            this.saveUpload(f);
        }
    }

    private String correctTime(String time){
        if(time.length() == 6){
            time = time.substring(0, 5) + "0" + time.charAt(time.length() - 1);
        }
        return time;
    }

}
