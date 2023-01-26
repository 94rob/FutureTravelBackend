package its.extratech.FutureTravel.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import its.extratech.FutureTravel.dtos.request.FintechRequest;
import its.extratech.FutureTravel.dtos.response.RecordDto;
import its.extratech.FutureTravel.servicies.implementations.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fintech")
public class FintechController {

    @Autowired
    FintechServiceImpl fintechService;

    @Autowired
    RecordServiceImpl recordService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/upload")
    public ResponseEntity<?>  UploadJson(@Valid @RequestBody List<FintechRequest> newRecord) {
        return new ResponseEntity<>( fintechService.saveUploadList(newRecord) ? HttpStatus.OK : HttpStatus.BAD_REQUEST );
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllData() throws JsonProcessingException {
        List<RecordDto> list = recordService.getAllByTipoDato('S');
        return new ResponseEntity<>(objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

}