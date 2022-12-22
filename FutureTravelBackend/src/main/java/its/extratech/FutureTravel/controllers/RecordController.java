package its.extratech.FutureTravel.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import its.extratech.FutureTravel.dtos.RecordFrontEndDto;
import its.extratech.FutureTravel.servicies.implementations.RecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    RecordServiceImpl recordServiceImpl;

    @GetMapping("/fetch/NON_CHIAMARE_ALTRIMENTI_DUPLICA_I_DATI_NEL_DATABASE")
    public ResponseEntity<?> fetch(){
        String s = recordServiceImpl.fetch();
        return new ResponseEntity<String>(s, HttpStatus.OK);
    }

    @GetMapping("/cerca")
    public ResponseEntity<?> returnRecordByProvincia(@RequestParam(name="provincia") String nomeProvincia) throws JsonProcessingException {
        List<RecordFrontEndDto> recordFrontEndDtoList = this.recordServiceImpl.findByNomeProvincia(nomeProvincia);
        ObjectMapper objectMapper = new ObjectMapper();
        return new ResponseEntity<String>(objectMapper.writeValueAsString(recordFrontEndDtoList), HttpStatus.OK);
    }
}
