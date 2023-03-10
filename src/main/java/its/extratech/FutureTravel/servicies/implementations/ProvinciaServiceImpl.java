package its.extratech.FutureTravel.servicies.implementations;

import its.extratech.FutureTravel.entities.Provincia;
import its.extratech.FutureTravel.repositories.ProvinciaRepository;
import its.extratech.FutureTravel.servicies.interfaces.IProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProvinciaServiceImpl implements IProvinciaService {

    @Autowired
    ProvinciaRepository provinciaRepository;

    public Provincia findById(String id) throws NoSuchElementException {
        return this.provinciaRepository.findById(id).get();
    }
}
