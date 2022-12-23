package its.extratech.FutureTravel.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import its.extratech.FutureTravel.dtos.RecordDtoPresenze;
import its.extratech.FutureTravel.servicies.implementations.ContestoServiceImpl;
import its.extratech.FutureTravel.servicies.implementations.RecordServiceImpl;
import its.extratech.FutureTravel.servicies.implementations.ResidenzaClientiServiceImpl;
import its.extratech.FutureTravel.servicies.implementations.TipoAlloggioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fintech")
public class FintechController {

    @Autowired
    private RecordServiceImpl recordService;

    @Autowired
    private ContestoServiceImpl contestoService;

    @Autowired
    private TipoAlloggioServiceImpl tipoAlloggioService;

    @Autowired
    private ResidenzaClientiServiceImpl residenzaClientiService;

    @Autowired
    private ObjectMapper objectMapper;


    @GetMapping("/all")
    public ResponseEntity<?> getAll() throws JsonProcessingException {
        List<RecordDtoPresenze> recordDtoPresenzeList = this.recordService.findAll();
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(recordDtoPresenzeList), HttpStatus.OK);
    }

    @GetMapping("/hotellike")
    public ResponseEntity<?> getByTipoAlloggioHotellike() throws JsonProcessingException {
        List<RecordDtoPresenze> recordDtoPresenzeList = this.recordService.selByIdTipoAlloggio("HOTELLIKE");
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(recordDtoPresenzeList), HttpStatus.OK);
    }

    @GetMapping("/other")
    public ResponseEntity<?> getByTipoAlloggioOther() throws JsonProcessingException {
        List<RecordDtoPresenze> recordDtoPresenzeList = this.recordService.selByIdTipoAlloggio("OTHER");
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(recordDtoPresenzeList), HttpStatus.OK);
    }

    @GetMapping("/it")
    public ResponseEntity<?> getByResidenzaClientiIt() throws JsonProcessingException {
        List<RecordDtoPresenze> recordDtoPresenzeList = this.recordService.selByIdResidenzaClienti("IT");
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(recordDtoPresenzeList), HttpStatus.OK);
    }

    @GetMapping("/estero")
    public ResponseEntity<?> getByResidenzaClientiEstero() throws JsonProcessingException {
        List<RecordDtoPresenze> recordDtoPresenzeList = this.recordService.selByIdResidenzaClienti("WRL_X_ITA");
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(recordDtoPresenzeList), HttpStatus.OK);
    }

    @GetMapping("/provincia/{idProvincia}")
    public ResponseEntity<?> getByProvincia(@PathVariable String idProvincia) throws JsonProcessingException {
        List<RecordDtoPresenze> recordDtoPresenzeList = this.recordService.selByIdProvincia(idProvincia);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(recordDtoPresenzeList), HttpStatus.OK);
    }

    @GetMapping("/time/{time}")
    public ResponseEntity<?> getByTime(@PathVariable String time) throws JsonProcessingException {
        List<RecordDtoPresenze> recordDtoPresenzeList = this.recordService.findByTime(time);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(recordDtoPresenzeList), HttpStatus.OK);
    }



}
