package its.extratech.FutureTravel.servicies.implementations;

import its.extratech.FutureTravel.dtos.ProvinciaDto;
import its.extratech.FutureTravel.entities.Provincia;
import its.extratech.FutureTravel.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinciaServiceImpl {

    @Autowired
    ProvinciaRepository provinciaRepository;

    public ProvinciaDto findByNomeProvincia(String nomeProvincia){
        Provincia provincia = this.provinciaRepository.findByNomeProvincia(nomeProvincia);
        return new ProvinciaDto(provincia.getId(), provincia.getNomeProvincia());
    }

    public Provincia findById(String id){
        return this.provinciaRepository.findById(id).get();
    }
}
