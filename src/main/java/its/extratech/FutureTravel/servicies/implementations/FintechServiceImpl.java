package its.extratech.FutureTravel.servicies.implementations;

import its.extratech.FutureTravel.dtos.request.FintechRequest;
import its.extratech.FutureTravel.entities.*;
import its.extratech.FutureTravel.entities.Record;
import its.extratech.FutureTravel.repositories.RecordRepository;
import its.extratech.FutureTravel.servicies.interfaces.IFintechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FintechServiceImpl implements IFintechService {

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

    public boolean saveUpload (FintechRequest request)
    {
        Record record = new Record();

        // Mostro in console il contesto del record che sto andando a salvare
        System.out.println(request.getProvincia()  + " - " + request.getTipoAlloggio() + " - " + request.getResidenzaClienti());

        // Cerco nel database il contesto giusto
        try{
            Provincia provincia = this.provinciaService.findById(request.getProvincia());
            TipoAlloggio tipoAlloggio = this.tipoAlloggioService.findById(request.getTipoAlloggio());
            ResidenzaClienti residenzaClienti = this.residenzaClientiService.findById(request.getResidenzaClienti());
            Contesto contesto = this.contestoService.findByProvinciaAndTipoAlloggioAndResidenzaClienti(provincia, tipoAlloggio, residenzaClienti);

            // Setto i diversi parametri del record e infine lo salvo nel database
            record.setContesto(contesto);
            record.setTime(this.correctTime(request.getTime()));
            record.setPresenze(request.getValue());
            record.setTipoDato('P');

            recordService.save(record);
            return true;

        } catch(NoSuchElementException ex){
            System.out.println("Contesto non trovato: inserisci il nuovo contesto per poter salvare i dati");
            return false;
        }
    }

    @Transactional(rollbackFor = NoSuchElementException.class)
    public boolean saveUploadList(List<FintechRequest> request)
    {
        boolean flag = true;
        for (FintechRequest f: request) {
            if(! this.saveUpload(f)) flag = false;
        }

        // Se tutti i record sono stati salvati senza problemi ritorna true, altrimenti false
        return flag;
    }

    // Per compatibilità con l'api Istat, correggo il formato delle date: per esempio 2008-1 diventa 2008-01
    private String correctTime(String time){
        if(time.length() == 6){
            time = time.substring(0, 5) + "0" + time.charAt(time.length() - 1);
        }
        return time;
    }

}
