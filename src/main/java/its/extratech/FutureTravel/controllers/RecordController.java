package its.extratech.FutureTravel.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import its.extratech.FutureTravel.dtos.response.RecordDtoCompleto;
import its.extratech.FutureTravel.servicies.implementations.RecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordServiceImpl recordServiceImpl;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/fetch")
    public ResponseEntity<?> fetch() throws IOException {
        //recordServiceImpl.fetch(); //Serve per fetchare e inserire i dati istat nel db
        return new ResponseEntity<>("Fatto", HttpStatus.OK);
    }

    @GetMapping("/cerca")
    public ResponseEntity<?> returnRecordByProvincia(@RequestParam(name="provincia") String nomeProvincia) throws JsonProcessingException {
        List<RecordDtoCompleto> recordDtoCompletoList = this.recordServiceImpl.selByNomeProvincia(nomeProvincia);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(recordDtoCompletoList), HttpStatus.OK);
    }
}
