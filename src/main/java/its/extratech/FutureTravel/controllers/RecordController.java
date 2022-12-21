package its.extratech.FutureTravel.controllers;

import its.extratech.FutureTravel.entities.Contesto;
import its.extratech.FutureTravel.entities.Record;
import its.extratech.FutureTravel.importData.XMLIstatReader;
import its.extratech.FutureTravel.servicies.ContestoService;
import its.extratech.FutureTravel.servicies.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    RecordService recordService;


    @GetMapping("/fetch")
    public ResponseEntity<?> fetch(){
        String s = recordService.fetch();

        return new ResponseEntity<String>(s, HttpStatus.OK);
    }
}
