package its.extratech.FutureTravel.servicies.implementations;

import its.extratech.FutureTravel.entities.TipoAlloggio;
import its.extratech.FutureTravel.repositories.TipoAlloggioRepository;
import its.extratech.FutureTravel.servicies.interfaces.ITipoAlloggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TipoAlloggioServiceImpl implements ITipoAlloggioService {

    @Autowired
    TipoAlloggioRepository tipoAlloggioRepository;

    public TipoAlloggio findById(String id) throws NoSuchElementException {
        return this.tipoAlloggioRepository.findById(id).get();
    }
}
