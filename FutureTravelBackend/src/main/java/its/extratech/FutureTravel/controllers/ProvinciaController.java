package its.extratech.FutureTravel.controllers;

import its.extratech.FutureTravel.dtos.ProvinciaDto;
import its.extratech.FutureTravel.entities.Provincia;
import its.extratech.FutureTravel.repositories.ProvinciaRepository;
import its.extratech.FutureTravel.servicies.implementations.ProvinciaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/provincia")
public class ProvinciaController {

    @Autowired
    public ProvinciaServiceImpl provinciaServiceImpl;

    @Autowired
    public ProvinciaRepository provinciaRepository;

    @GetMapping("/cerca")
    public ResponseEntity<?> returnProvincia(@RequestParam(name="nome") String nomeProvincia){
        ProvinciaDto provinciaDto = this.provinciaServiceImpl.findByNomeProvincia(nomeProvincia);
        return new ResponseEntity<>(provinciaDto, HttpStatus.OK);
    }

    @GetMapping("/getprovincia")
    public List<Provincia> getProvincia() {
        return provinciaRepository.FindAllProvicia();
    }


}
