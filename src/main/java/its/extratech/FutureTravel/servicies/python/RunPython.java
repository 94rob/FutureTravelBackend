package its.extratech.FutureTravel.servicies.python;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import its.extratech.FutureTravel.entities.*;
import its.extratech.FutureTravel.entities.Record;
import its.extratech.FutureTravel.servicies.implementations.*;
import org.hibernate.sql.ast.tree.predicate.PredicateContainer;
import org.python.util.PythonInterpreter;



import java.io.*;
import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

public class RunPython {


    @Autowired
    TipoAlloggioServiceImpl tipoAlloggioService;

    @Autowired
    ProvinciaServiceImpl provinciaService;

    @Autowired
    ResidenzaClientiServiceImpl residenzaClientiService;

    @Autowired
    ContestoServiceImpl contestoService;

    @Autowired
    RecordServiceImpl recordService;

    public void JSON() {
        Map<String, String> result;
        try (PythonInterpreter pyInterp = new PythonInterpreter()) {
            StringWriter output = new StringWriter();
            pyInterp.setOut(output);

            pyInterp.exec("x =  '[{\"Provincia\" : \"Caserta\",\"Tipo_alloggio\" : \"HOTELLIKE\",\"Residenza_clienti\" : \"IT\", \"Time\" : \"2008-01\", \"Presenze\" : \"12874\"}]'\n" +
                    "\n" +
                    "return x\n");



            result = new ObjectMapper().readValue((output.toString()), HashMap.class);

        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        //Creazione del nuovo record
        Record record = new Record();

        TipoAlloggio tipoAlloggio = this.tipoAlloggioService.findById(result.get("Tipo_alloggio"));
        Provincia provincia = this.provinciaService.findByNomeProvincia(result.get("Provincia"));
        ResidenzaClienti residenzaClienti = this.residenzaClientiService.findById(result.get("Residenza_clienti"));

        Contesto contesto = this.contestoService.findByProvinciaAndTipoAlloggioAndResidenzaClienti(provincia, tipoAlloggio, residenzaClienti);

        record.setContesto(contesto);
        record.setTime(result.get("Time"));
        record.setPresenze(Integer.parseInt(result.get("Presenze")));
        record.setTipodato('P');

        System.out.println(record.toString());

        this.recordService.save(record);
    }
}
