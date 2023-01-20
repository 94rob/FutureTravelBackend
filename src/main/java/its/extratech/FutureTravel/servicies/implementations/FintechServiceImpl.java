package its.extratech.FutureTravel.servicies.implementations;

import its.extratech.FutureTravel.dtos.request.FintechRequest;
import its.extratech.FutureTravel.entities.*;
import its.extratech.FutureTravel.entities.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void saveUpload (FintechRequest request)
    {
        Record record = new Record();
        Provincia provincia = this.provinciaService.findByNomeProvincia(request.getProvincia());
        TipoAlloggio tipoAlloggio = this.tipoAlloggioService.findById(request.getTipoAlloggio());
        ResidenzaClienti residenzaClienti = this.residenzaClientiService.findByDescrizione(request.getResidenzaClienti());

        Contesto contesto = this.contestoService.findByProvinciaAndTipoAlloggioAndResidenzaClienti(provincia, tipoAlloggio, residenzaClienti);

        record.setContesto(contesto);
        record.setTime(request.getTime());
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

}
