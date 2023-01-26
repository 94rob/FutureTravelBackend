package its.extratech.FutureTravel.servicies.interfaces;

import its.extratech.FutureTravel.entities.ResidenzaClienti;

import java.util.NoSuchElementException;

public interface IResidenzaClientiService {

    ResidenzaClienti findById(String id) throws NoSuchElementException;
}
