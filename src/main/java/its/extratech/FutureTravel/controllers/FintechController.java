package its.extratech.FutureTravel.controllers;

import its.extratech.FutureTravel.dtos.request.FintechRequest;
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

    @PostMapping("/upload")
    public ResponseEntity<?>  UploadJson(@Valid @RequestBody List<FintechRequest> newRecord) {
        fintechService.saveListUpload(newRecord);
        return new ResponseEntity<>("Record previsionali inseriti", HttpStatus.OK);
    }

}