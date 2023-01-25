package its.extratech.FutureTravel.servicies.interfaces;

import its.extratech.FutureTravel.entities.TipoAlloggio;

import java.util.NoSuchElementException;

public interface ITipoAlloggioService {

    TipoAlloggio findById(String id) throws NoSuchElementException;
}
