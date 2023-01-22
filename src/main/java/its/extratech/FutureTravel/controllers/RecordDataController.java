package its.extratech.FutureTravel.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import its.extratech.FutureTravel.dtos.response.RecordDtoCompleto;
import its.extratech.FutureTravel.servicies.implementations.RecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{tipoDato}")
public class RecordDataController {

    @Autowired
    private RecordServiceImpl recordService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/IT/whatever")
    public ResponseEntity<?> getByResidenzaIt(@Param("startDate") String startDate,
                                              @Param("endDate") String endDate,
                                              @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selByIdResidenza(tipoDato, "IT", startDate, endDate);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/IT/whatever/{idProvincia}")
    public ResponseEntity<?> getByResidenzaItByProvincia(@Param("startDate") String startDate,
                                                         @Param("endDate") String endDate,
                                                         @PathVariable("idProvincia") String idProvincia,
                                                         @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selByIdResidenzaByIdProvincia(tipoDato, "IT", startDate, endDate, idProvincia);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/IT/HOTELLIKE")
    public ResponseEntity<?> getByResidenzaItAndAlloggioHotellike(@Param("startDate") String startDate,
                                                                      @Param("endDate") String endDate,
                                                                  @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selByIdResidenzaAndIdAlloggio(tipoDato, "IT", "HOTELLIKE", startDate, endDate);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/IT/HOTELLIKE/{idProvincia}")
    public ResponseEntity<?> getByResidenzaItAndAlloggioHotellikeByProvincia(@Param("startDate") String startDate,
                                                                                 @Param("endDate") String endDate,
                                                                                 @PathVariable("idProvincia") String idProvincia,
                                                                             @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selByIdResidenzaAndIdAlloggioByIdProvincia(tipoDato, "IT", "HOTELLIKE", startDate, endDate, idProvincia);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/IT/OTHER")
    public ResponseEntity<?> getByResidenzaItAndAlloggioOther(@Param("startDate") String startDate,
                                                                  @Param("endDate") String endDate,
                                                              @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selByIdResidenzaAndIdAlloggio(tipoDato, "IT", "OTHER", startDate, endDate);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/IT/OTHER/{idProvincia}")
    public ResponseEntity<?> getByResidenzaItAndAlloggioOtherByProvincia(@Param("startDate") String startDate,
                                                                                 @Param("endDate") String endDate,
                                                                                 @PathVariable("idProvincia") String idProvincia,
                                                                         @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selByIdResidenzaAndIdAlloggioByIdProvincia(tipoDato, "IT", "OTHER", startDate, endDate, idProvincia);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/IT/ALL")
    public ResponseEntity<?> getByResidenzaItAndAlloggioAll(@Param("startDate") String startDate,
                                                                @Param("endDate") String endDate,
                                                            @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selByIdResidenzaAndAlloggioAll(tipoDato, "IT", startDate, endDate);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/IT/ALL/{idProvincia}")
    public ResponseEntity<?> getByResidenzaItAndAlloggioAllByProvincia(@Param("startDate") String startDate,
                                                            @Param("endDate") String endDate,
                                                            @PathVariable("idProvincia") String idProvincia,
                                                                       @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selByIdResidenzaAndAlloggioAllByProvincia(tipoDato, "IT", startDate, endDate, idProvincia);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/WRL_X_ITA/whatever")
    public ResponseEntity<?> getByResidenzaEstero(@Param("startDate") String startDate,
                                                  @Param("endDate") String endDate,
                                                  @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selByIdResidenza(tipoDato, "WRL_X_ITA", startDate, endDate);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/WRL_X_ITA/whatever/{idProvincia}")
    public ResponseEntity<?> getByResidenzaEsteroByProvincia(@Param("startDate") String startDate,
                                                             @Param("endDate") String endDate,
                                                             @PathVariable("idProvincia") String idProvincia,
                                                             @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selByIdResidenzaByIdProvincia(tipoDato, "WRL_X_ITA", startDate, endDate, idProvincia);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/WRL_X_ITA/HOTELLIKE")
    public ResponseEntity<?> getByResidenzaEsteroAndAlloggioHotellike(@Param("startDate") String startDate,
                                                                      @Param("endDate") String endDate,
                                                                      @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selByIdResidenzaAndIdAlloggio(tipoDato, "WRL_X_ITA", "HOTELLIKE", startDate, endDate);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/WRL_X_ITA/HOTELLIKE/{idProvincia}")
    public ResponseEntity<?> getByResidenzaEsteroAndAlloggioHotellikeByProvincia(@Param("startDate") String startDate,
                                                                                 @Param("endDate") String endDate,
                                                                                 @PathVariable("idProvincia") String idProvincia,
                                                                                 @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selByIdResidenzaAndIdAlloggioByIdProvincia(tipoDato, "WRL_X_ITA", "HOTELLIKE", startDate, endDate, idProvincia);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/WRL_X_ITA/OTHER")
    public ResponseEntity<?> getByResidenzaEsteroAndAlloggioOther(@Param("startDate") String startDate,
                                                                  @Param("endDate") String endDate,
                                                                  @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selByIdResidenzaAndIdAlloggio(tipoDato, "WRL_X_ITA", "OTHER", startDate, endDate);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/WRL_X_ITA/OTHER/{idProvincia}")
    public ResponseEntity<?> getByResidenzaEsteroAndAlloggioOtherByProvincia(@Param("startDate") String startDate,
                                                                             @Param("endDate") String endDate,
                                                                             @PathVariable("idProvincia") String idProvincia,
                                                                             @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selByIdResidenzaAndIdAlloggioByIdProvincia(tipoDato, "WRL_X_ITA", "OTHER", startDate, endDate, idProvincia);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/WRL_X_ITA/ALL")
    public ResponseEntity<?> getByResidenzaEsteroAndAlloggioAll(@Param("startDate") String startDate,
                                                     @Param("endDate") String endDate,
                                                                @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selByIdResidenzaAndAlloggioAll(tipoDato, "WRL_X_ITA", startDate, endDate);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/WRL_X_ITA/ALL/{idProvincia}")
    public ResponseEntity<?> getByResidenzaEsteroAndAlloggioAllByProvincia(@Param("startDate") String startDate,
                                                                @Param("endDate") String endDate,
                                                                @PathVariable("idProvincia") String idProvincia,
                                                                           @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selByIdResidenzaAndAlloggioAllByProvincia(tipoDato, "WRL_X_ITA", startDate, endDate, idProvincia);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/ALL/whatever")
    public ResponseEntity<?> getByResidenzaAll(@Param("startDate") String startDate,
                                                     @Param("endDate") String endDate,
                                               @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selByIdResidenzaAll(tipoDato, startDate, endDate);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/ALL/whatever/{idProvincia}")
    public ResponseEntity<?> getByResidenzaAllByProvincia(@Param("startDate") String startDate,
                                                          @Param("endDate") String endDate,
                                                          @PathVariable("idProvincia") String idProvincia,
                                                          @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selByIdResidenzaAllByProvincia(tipoDato, startDate, endDate, idProvincia);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/ALL/HOTELLIKE")
    public ResponseEntity<?> getByResidenzaAllAndAlloggioHotellike(@Param("startDate") String startDate,
                                                           @Param("endDate") String endDate,
                                                                   @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selByIdTipoAlloggioAndIdResidenzaAll(tipoDato, "HOTELLIKE", startDate, endDate);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/ALL/HOTELLIKE/{idProvincia}")
    public ResponseEntity<?> getByResidenzaAllAndAlloggioHotellikeByIdProvincia(@Param("startDate") String startDate,
                                                                        @Param("endDate") String endDate,
                                                                        @PathVariable("idProvincia") String idProvincia,
                                                                                @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selByIdTipoAlloggioAndIdResidenzaAllByProvincia(tipoDato, "HOTELLIKE", startDate, endDate, idProvincia);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/ALL/OTHER")
    public ResponseEntity<?> getByResidenzaAllAndAlloggioOther(@Param("startDate") String startDate,
                                                       @Param("endDate") String endDate,
                                                               @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selByIdTipoAlloggioAndIdResidenzaAll(tipoDato, "OTHER", startDate, endDate);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/ALL/OTHER/{idProvincia}")
    public ResponseEntity<?> getByResidenzaAllAndAlloggioOtherByIdProvincia(@Param("startDate") String startDate,
                                                                    @Param("endDate") String endDate,
                                                                    @PathVariable("idProvincia") String idProvincia,
                                                                            @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selByIdTipoAlloggioAndIdResidenzaAllByProvincia(tipoDato, "OTHER", startDate, endDate, idProvincia);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/ALL/ALL")
    public ResponseEntity<?> getByResidenzaAllAndAlloggioAll(@Param("startDate") String startDate,
                                             @Param("endDate") String endDate,
                                                             @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selByResidenzaAllAndAlloggioAll(tipoDato, startDate, endDate);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/ALL/ALL/{idProvincia}")
    public ResponseEntity<?> getByResidenzaAllAndAlloggioAllByProvincia(@Param("startDate") String startDate,
                                                        @Param("endDate") String endDate,
                                                        @PathVariable("idProvincia") String idProvincia,
                                                                        @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selByResidenzaAllAndAlloggioAllByProvincia(tipoDato, startDate, endDate, idProvincia);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }


    @GetMapping("/whatever/whatever")
    public ResponseEntity<?> getAll(@Param("startDate") String startDate,
                                             @Param("endDate") String endDate,
                                    @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selAll(tipoDato, startDate, endDate);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/whatever/whatever/{idProvincia}")
    public ResponseEntity<?> getAllByProvincia(@Param("startDate") String startDate,
                                                        @Param("endDate") String endDate,
                                                        @PathVariable("idProvincia") String idProvincia,
                                               @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selAllByProvincia(tipoDato, startDate, endDate, idProvincia);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/whatever/HOTELLIKE")
    public ResponseEntity<?> getByAlloggioHotellike(@Param("startDate") String startDate,
                                                        @Param("endDate") String endDate,
                                                    @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selByIdTipoAlloggio(tipoDato, "HOTELLIKE", startDate, endDate);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/whatever/HOTELLIKE/{idProvincia}")
    public ResponseEntity<?> getByAlloggioHotellikeByProvincia(@Param("startDate") String startDate,
                                                                   @Param("endDate") String endDate,
                                                                   @PathVariable("idProvincia") String idProvincia,
                                                               @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selByIdTipoAlloggioByProvincia(tipoDato, "HOTELLIKE", startDate, endDate, idProvincia);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/whatever/OTHER")
    public ResponseEntity<?> getByAlloggioOther(@Param("startDate") String startDate,
                                                @Param("endDate") String endDate,
                                                @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selByIdTipoAlloggio(tipoDato, "OTHER", startDate, endDate);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/whatever/OTHER/{idProvincia}")
    public ResponseEntity<?> getByAlloggioOtherByProvincia(@Param("startDate") String startDate,
                                                           @Param("endDate") String endDate,
                                                           @PathVariable("idProvincia") String idProvincia,
                                                           @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selByIdTipoAlloggioByProvincia(tipoDato, "OTHER", startDate, endDate, idProvincia);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/whatever/ALL")
    public ResponseEntity<?> getByAlloggioAll(@Param("startDate") String startDate,
                                              @Param("endDate") String endDate,
                                              @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selByIdAlloggioAll(tipoDato, startDate, endDate);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

    @GetMapping("/whatever/ALL/{idProvincia}")
    public ResponseEntity<?> getByAlloggioAllByProvincia(@Param("startDate") String startDate,
                                                         @Param("endDate") String endDate,
                                                         @PathVariable("idProvincia") String idProvincia,
                                                         @PathVariable("tipoDato") char tipoDato) throws JsonProcessingException {
        List<RecordDtoCompleto> list = recordService.selByIdAlloggioAllByProvincia(tipoDato, startDate, endDate, idProvincia);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(list), HttpStatus.OK);
    }

}
