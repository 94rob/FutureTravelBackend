package its.extratech.FutureTravel.servicies;

import its.extratech.FutureTravel.entities.Provincia;
import its.extratech.FutureTravel.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinciaService {

    @Autowired
    ProvinciaRepository provinciaRepository;

    public Provincia findByNomeProvincia(String nomeProvincia){
        return this.provinciaRepository.findByNomeProvincia(nomeProvincia);
    }
}
