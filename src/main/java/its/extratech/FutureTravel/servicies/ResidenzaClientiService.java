package its.extratech.FutureTravel.servicies;

import its.extratech.FutureTravel.entities.ResidenzaClienti;
import its.extratech.FutureTravel.repositories.ResidenzaClientiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResidenzaClientiService {

    @Autowired
    ResidenzaClientiRepository residenzaClientiRepository;

    public ResidenzaClienti findById(String id){
        return this.residenzaClientiRepository.findById(id).get();
    }
}
