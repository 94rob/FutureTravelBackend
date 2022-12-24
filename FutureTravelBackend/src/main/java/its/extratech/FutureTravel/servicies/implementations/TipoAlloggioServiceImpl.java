package its.extratech.FutureTravel.servicies.implementations;

import its.extratech.FutureTravel.entities.TipoAlloggio;
import its.extratech.FutureTravel.repositories.TipoAlloggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoAlloggioServiceImpl {

    @Autowired
    TipoAlloggioRepository tipoAlloggioRepository;

    public TipoAlloggio findById(String id){
        return this.tipoAlloggioRepository.findById(id).get();
    }
}
