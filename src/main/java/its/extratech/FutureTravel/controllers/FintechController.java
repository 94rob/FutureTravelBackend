package its.extratech.FutureTravel.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import its.extratech.FutureTravel.dtos.request.FintechRequest;
import its.extratech.FutureTravel.dtos.response.RecordDtoPresenze;
import its.extratech.FutureTravel.entities.Record;
import its.extratech.FutureTravel.servicies.implementations.*;
import its.extratech.FutureTravel.entities.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fintech")
public class FintechController {

    @Autowired
    TipoAlloggioServiceImpl tipoAlloggioService;

    @Autowired
    ProvinciaServiceImpl provinciaService;

    @Autowired
    ResidenzaClientiServiceImpl residenzaClientiService;

    @Autowired
    ContestoServiceImpl contestoService;


    @Autowired
    private RecordServiceImpl recordService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    FintechServiceImpl fintechService;

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
        List<RecordDtoPresenze> recordDtoPresenzeList = this.recordService.selByIdResidenza("IT");
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(recordDtoPresenzeList), HttpStatus.OK);
    }

    @GetMapping("/estero")
    public ResponseEntity<?> getByResidenzaClientiEstero() throws JsonProcessingException {
        List<RecordDtoPresenze> recordDtoPresenzeList = this.recordService.selByIdResidenza("WRL_X_ITA");
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

    @PostMapping("/upload")
    public ResponseEntity<?>  UploadJson(@Valid @RequestBody List<FintechRequest> newRecord) throws JsonProcessingException {

        //fintechService.saveUpload(newRecord);
        fintechService.saveListUpload(newRecord);
        return new ResponseEntity<>(newRecord.toString(), HttpStatus.OK);
    }

}