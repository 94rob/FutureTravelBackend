package its.extratech.FutureTravel.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import its.extratech.FutureTravel.dtos.response.RecordDto;
import its.extratech.FutureTravel.servicies.implementations.RecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/{tipoDato}")
public class RecordDataController {

    @Autowired
    private RecordServiceImpl recordService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/{idResidenza}/{idAlloggio}")
    public ResponseEntity<?> getByResidenzaAndAlloggio(@Param("startDate") String startDate,
                                                       @Param("endDate") String endDate,
                                                       @PathVariable("idResidenza") String idResidenza,
                                                       @PathVariable("idAlloggio") String idAlloggio,
                                                       @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDto> list = recordService.getByResidenzaAndAlloggio(tipoDato, idResidenza, idAlloggio, startDate, endDate);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/{idResidenza}/{idAlloggio}/{idProvincia}")
    public ResponseEntity<?> getByResidenzaAndAlloggioByProvincia(@Param("startDate") String startDate,
                                                                  @Param("endDate") String endDate,
                                                                  @PathVariable("idResidenza") String idResidenza,
                                                                  @PathVariable("idAlloggio") String idAlloggio,
                                                                  @PathVariable("idProvincia") String idProvincia,
                                                                  @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDto> list = recordService.getByResidenzaAndAlloggioByProvincia(tipoDato, idResidenza, idAlloggio, idProvincia, startDate, endDate);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }
}
