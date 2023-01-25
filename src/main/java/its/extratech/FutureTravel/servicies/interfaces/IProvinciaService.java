package its.extratech.FutureTravel.servicies.interfaces;

import its.extratech.FutureTravel.entities.Provincia;

import java.util.NoSuchElementException;

public interface IProvinciaService {

    Provincia findById(String id) throws NoSuchElementException;
}
