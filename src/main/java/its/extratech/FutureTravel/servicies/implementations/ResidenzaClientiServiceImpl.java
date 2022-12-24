package its.extratech.FutureTravel.servicies.implementations;

import its.extratech.FutureTravel.entities.ResidenzaClienti;
import its.extratech.FutureTravel.repositories.ResidenzaClientiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ResidenzaClientiServiceImpl {

    @Autowired
    ResidenzaClientiRepository residenzaClientiRepository;

    public ResidenzaClienti findById(String id) throws NoSuchElementException {
        return this.residenzaClientiRepository.findById(id).get();
    }
}
