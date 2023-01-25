package its.extratech.FutureTravel.servicies.interfaces;

import its.extratech.FutureTravel.entities.Contesto;
import its.extratech.FutureTravel.entities.Provincia;
import its.extratech.FutureTravel.entities.ResidenzaClienti;
import its.extratech.FutureTravel.entities.TipoAlloggio;

public interface IContestoService {

    Contesto findByProvinciaAndTipoAlloggioAndResidenzaClienti(Provincia provincia, TipoAlloggio tipoAlloggio, ResidenzaClienti residenzaClienti);

}
