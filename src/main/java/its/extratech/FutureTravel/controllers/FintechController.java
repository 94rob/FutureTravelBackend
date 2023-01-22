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
    FintechServiceImpl fintechService;

    @PostMapping("/upload")
    public ResponseEntity<?>  UploadJson(@Valid @RequestBody List<FintechRequest> newRecord) throws JsonProcessingException {
        fintechService.saveListUpload(newRecord);
        return new ResponseEntity<>(newRecord.toString(), HttpStatus.OK);
    }

}