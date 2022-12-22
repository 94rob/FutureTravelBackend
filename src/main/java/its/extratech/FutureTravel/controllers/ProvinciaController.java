package its.extratech.FutureTravel.controllers;

import its.extratech.FutureTravel.dtos.ProvinciaDto;
import its.extratech.FutureTravel.servicies.implementations.ProvinciaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provincia")
public class ProvinciaController {

    @Autowired
    public ProvinciaServiceImpl provinciaServiceImpl;

    @GetMapping("/cerca")
    public ResponseEntity<?> returnProvincia(@RequestParam(name="nome") String nomeProvincia){
        ProvinciaDto provinciaDto = this.provinciaServiceImpl.findByNomeProvincia(nomeProvincia);
        return new ResponseEntity<>(provinciaDto, HttpStatus.OK);
    }


}
