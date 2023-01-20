package its.extratech.FutureTravel.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public void  UploadJson(@Valid @RequestBody Record newRecord) throws JsonProcessingException {
        Map<String, String> result;
        result = new ObjectMapper().readValue((newRecord.toString()), HashMap.class);
        Record record = new Record();

        TipoAlloggio tipoAlloggio = this.tipoAlloggioService.findById(result.get("Tipo_alloggio"));
        Provincia provincia = this.provinciaService.findByNomeProvincia(result.get("Provincia"));
        ResidenzaClienti residenzaClienti = this.residenzaClientiService.findById(result.get("Residenza_clienti"));

        Contesto contesto = this.contestoService.findByProvinciaAndTipoAlloggioAndResidenzaClienti(provincia, tipoAlloggio, residenzaClienti);

        record.setContesto(contesto);
        record.setTime(result.get("Time"));
        record.setPresenze(Integer.parseInt(result.get("Presenze")));
        record.setTipoDato('P');

        System.out.println(record);

        //recordService.save(record);
    }

}