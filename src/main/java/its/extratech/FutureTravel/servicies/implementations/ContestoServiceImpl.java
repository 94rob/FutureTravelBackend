package its.extratech.FutureTravel.servicies.implementations;

import its.extratech.FutureTravel.entities.Contesto;
import its.extratech.FutureTravel.entities.Provincia;
import its.extratech.FutureTravel.entities.ResidenzaClienti;
import its.extratech.FutureTravel.entities.TipoAlloggio;
import its.extratech.FutureTravel.repositories.ContestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContestoServiceImpl {

    @Autowired
    ContestoRepository contestoRepository;

    public void save(Contesto c){
        this.contestoRepository.save(c);
    }

    public Contesto findByProvinciaAndTipoAlloggioAndResidenzaClienti(Provincia provincia, TipoAlloggio tipoAlloggio, ResidenzaClienti residenzaClienti){
        return this.contestoRepository.findByProvinciaAndTipoAlloggioAndResidenzaClienti(provincia, tipoAlloggio, residenzaClienti).get(0);
    }

}
