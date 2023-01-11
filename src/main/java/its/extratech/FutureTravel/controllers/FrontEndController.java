package its.extratech.FutureTravel.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import its.extratech.FutureTravel.dtos.RecordDtoCompleto;
import its.extratech.FutureTravel.servicies.implementations.RecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/front")
public class FrontEndController {

    @Autowired
    private RecordServiceImpl recordService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/alloggio/hotellike")
    public ResponseEntity<?> getByTipoAlloggioHotellike(@Param("startDate") String startDate,
                                                        @Param("endDate") String endDate) throws JsonProcessingException {
        List<RecordDtoCompleto> recordDtoCompletoList =
                startDate == null ? this.recordService.selByIdTipoAlloggioFE("HOTELLIKE") :
                endDate == null ? this.recordService.selByIdTipoAlloggioFE("HOTELLIKE", startDate) :
                this.recordService.selByIdTipoAlloggioFE("HOTELLIKE", startDate, endDate);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(recordDtoCompletoList), HttpStatus.OK);
    }

    @GetMapping("/alloggio/hotellike/{idProvincia}")
    public ResponseEntity<?> getByTipoAlloggioHotellikeByProvincia(@Param("startDate") String startDate,
                                                                   @Param("endDate") String endDate,
                                                                   @PathVariable("idProvincia") String idProvincia) throws JsonProcessingException {
        List<RecordDtoCompleto> recordDtoCompletoList =
                startDate == null ? this.recordService.selByIdTipoAlloggioAndByIdProvinciaFE("HOTELLIKE", idProvincia) :
                        endDate == null ? this.recordService.selByIdTipoAlloggioAndByIdProvinciaFE("HOTELLIKE", startDate, idProvincia) :
                                this.recordService.selByIdTipoAlloggioAndByIdProvinciaFE("HOTELLIKE", startDate, endDate, idProvincia);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(recordDtoCompletoList), HttpStatus.OK);
    }

    @GetMapping("/alloggio/other")
    public ResponseEntity<?> getByTipoAlloggioOther(@Param("startDate") String startDate,
                                                    @Param("endDate") String endDate) throws JsonProcessingException {
        List<RecordDtoCompleto> recordDtoCompletoList =
                startDate == null ? this.recordService.selByIdTipoAlloggioFE("OTHER") :
                        endDate == null ? this.recordService.selByIdTipoAlloggioFE("OTHER", startDate) :
                                this.recordService.selByIdTipoAlloggioFE("OTHER", startDate, endDate);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(recordDtoCompletoList), HttpStatus.OK);
    }

    @GetMapping("/alloggio/other/{idProvincia}")
    public ResponseEntity<?> getByTipoAlloggioOtherByProvincia(@Param("startDate") String startDate,
                                                               @Param("endDate") String endDate,
                                                               @PathVariable("idProvincia") String idProvincia) throws JsonProcessingException {
        List<RecordDtoCompleto> recordDtoCompletoList =
                startDate == null ? this.recordService.selByIdTipoAlloggioAndByIdProvinciaFE("OTHER", idProvincia) :
                        endDate == null ? this.recordService.selByIdTipoAlloggioAndByIdProvinciaFE("OTHER", startDate, idProvincia) :
                                this.recordService.selByIdTipoAlloggioAndByIdProvinciaFE("OTHER", startDate, endDate, idProvincia);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(recordDtoCompletoList), HttpStatus.OK);
    }

    @GetMapping("/abs/alloggio/hotellike")
    public ResponseEntity<?> getByTipoAlloggioHotellikeAbs(@Param("startDate") String startDate,
                                                           @Param("endDate") String endDate) throws JsonProcessingException {
        List<RecordDtoCompleto> recordDtoCompletoList =
                startDate == null ? this.recordService.selByIdTipoAlloggioAbsFE("HOTELLIKE") :
                        endDate == null ? this.recordService.selByIdTipoAlloggioAbsFE("HOTELLIKE", startDate) :
                                this.recordService.selByIdTipoAlloggioAbsFE("HOTELLIKE", startDate, endDate);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(recordDtoCompletoList), HttpStatus.OK);
    }

    @GetMapping("/abs/alloggio/hotellike/{idProvincia}")
    public ResponseEntity<?> getByTipoAlloggioHotellikeAbsByIdProvincia(@Param("startDate") String startDate,
                                                                        @Param("endDate") String endDate,
                                                                        @PathVariable("idProvincia") String idProvincia) throws JsonProcessingException {
        List<RecordDtoCompleto> recordDtoCompletoList =
                startDate == null ? this.recordService.selByIdTipoAlloggioAndByIdProvinciaAbsFE("HOTELLIKE", idProvincia) :
                        endDate == null ? this.recordService.selByIdTipoAlloggioAndByIdProvinciaAbsFE("HOTELLIKE", startDate, idProvincia) :
                                this.recordService.selByIdTipoAlloggioAndByIdProvinciaAbsFE("HOTELLIKE", startDate, endDate, idProvincia);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(recordDtoCompletoList), HttpStatus.OK);
    }

    @GetMapping("/abs/alloggio/other")
    public ResponseEntity<?> getByTipoAlloggioOtherAbs(@Param("startDate") String startDate,
                                                       @Param("endDate") String endDate) throws JsonProcessingException {
        List<RecordDtoCompleto> recordDtoCompletoList =
                startDate == null ? this.recordService.selByIdTipoAlloggioAbsFE("OTHER") :
                        endDate == null ? this.recordService.selByIdTipoAlloggioAbsFE("OTHER", startDate) :
                                this.recordService.selByIdTipoAlloggioAbsFE("OTHER", startDate, endDate);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(recordDtoCompletoList), HttpStatus.OK);
    }

    @GetMapping("/abs/alloggio/other/{idProvincia}")
    public ResponseEntity<?> getByTipoAlloggioOtherAbsByIdProvincia(@Param("startDate") String startDate,
                                                                    @Param("endDate") String endDate,
                                                                    @PathVariable("idProvincia") String idProvincia) throws JsonProcessingException {
        List<RecordDtoCompleto> recordDtoCompletoList =
                startDate == null ? this.recordService.selByIdTipoAlloggioAndByIdProvinciaAbsFE("OTHER", idProvincia) :
                        endDate == null ? this.recordService.selByIdTipoAlloggioAndByIdProvinciaAbsFE("OTHER", startDate, idProvincia) :
                                this.recordService.selByIdTipoAlloggioAndByIdProvinciaAbsFE("OTHER", startDate, endDate, idProvincia);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(recordDtoCompletoList), HttpStatus.OK);
    }

    @GetMapping("/residenza/it")
    public ResponseEntity<?> getByResidenzaIt(@Param("startDate") String startDate,
                                              @Param("endDate") String endDate) throws JsonProcessingException {
        List<RecordDtoCompleto> recordDtoCompletoList =
                startDate == null ? this.recordService.selByIdResidenzaClientiFE("IT") :
                        endDate == null ? this.recordService.selByIdResidenzaClientiFE("IT", startDate) :
                                this.recordService.selByIdResidenzaClientiFE("IT", startDate, endDate);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(recordDtoCompletoList), HttpStatus.OK);
    }

    @GetMapping("/residenza/it/{idProvincia}")
    public ResponseEntity<?> getByResidenzaItByProvincia(@Param("startDate") String startDate,
                                                         @Param("endDate") String endDate,
                                                         @PathVariable("idProvincia") String idProvincia) throws JsonProcessingException {
        List<RecordDtoCompleto> recordDtoCompletoList =
                startDate == null ? this.recordService.selByIdResidenzaClientiAndByIdProvinciaFE("IT", idProvincia) :
                        endDate == null ? this.recordService.selByIdResidenzaClientiAndByIdProvinciaFE("IT", startDate, idProvincia) :
                                this.recordService.selByIdResidenzaClientiAndByIdProvinciaFE("IT", startDate, endDate, idProvincia);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(recordDtoCompletoList), HttpStatus.OK);
    }

    @GetMapping("/residenza/estero")
    public ResponseEntity<?> getByResidenzaEstero(@Param("startDate") String startDate,
                                                  @Param("endDate") String endDate) throws JsonProcessingException {
        List<RecordDtoCompleto> recordDtoCompletoList =
                startDate == null ? this.recordService.selByIdResidenzaClientiFE("WRL_X_ITA") :
                        endDate == null ? this.recordService.selByIdResidenzaClientiFE("WRL_X_ITA", startDate) :
                                this.recordService.selByIdResidenzaClientiFE("WRL_X_ITA", startDate, endDate);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(recordDtoCompletoList), HttpStatus.OK);
    }

    @GetMapping("/residenza/estero/{idProvincia}")
    public ResponseEntity<?> getByResidenzaEsteroByProvincia(@Param("startDate") String startDate,
                                                             @Param("endDate") String endDate,
                                                             @PathVariable("idProvincia") String idProvincia) throws JsonProcessingException {
        List<RecordDtoCompleto> recordDtoCompletoList =
                startDate == null ? this.recordService.selByIdResidenzaClientiAndByIdProvinciaFE("WRL_X_ITA", idProvincia) :
                        endDate == null ? this.recordService.selByIdResidenzaClientiAndByIdProvinciaFE("WRL_X_ITA", startDate, idProvincia) :
                                this.recordService.selByIdResidenzaClientiAndByIdProvinciaFE("WRL_X_ITA", startDate, endDate, idProvincia);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(recordDtoCompletoList), HttpStatus.OK);
    }

    @GetMapping("/abs/residenza/it")
    public ResponseEntity<?> getByResidenzaItAbs(@Param("startDate") String startDate,
                                                 @Param("endDate") String endDate) throws JsonProcessingException {
        List<RecordDtoCompleto> recordDtoCompletoList =
                startDate == null ? this.recordService.selByIdResidenzaClientiAbsFE("IT") :
                        endDate == null ? this.recordService.selByIdResidenzaClientiAbsFE("IT", startDate) :
                                this.recordService.selByIdResidenzaClientiAbsFE("IT", startDate, endDate);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(recordDtoCompletoList), HttpStatus.OK);
    }

    @GetMapping("/abs/residenza/it/{idProvincia}")
    public ResponseEntity<?> getByResidenzaItAbsByProvincia(@Param("startDate") String startDate,
                                                            @Param("endDate") String endDate,
                                                            @PathVariable("idProvincia") String idProvincia) throws JsonProcessingException {
        List<RecordDtoCompleto> recordDtoCompletoList =
                startDate == null ? this.recordService.selByIdResidenzaClientiAndByIdProvinciaAbsFE("IT", idProvincia) :
                        endDate == null ? this.recordService.selByIdResidenzaClientiAndByIdProvinciaAbsFE("IT", startDate, idProvincia) :
                                this.recordService.selByIdResidenzaClientiAndByIdProvinciaAbsFE("IT", startDate, endDate, idProvincia);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(recordDtoCompletoList), HttpStatus.OK);
    }

    @GetMapping("/abs/residenza/estero")
    public ResponseEntity<?> getByResidenzaEsteroAbs(@Param("startDate") String startDate,
                                                     @Param("endDate") String endDate) throws JsonProcessingException {
        List<RecordDtoCompleto> recordDtoCompletoList =
                startDate == null ? this.recordService.selByIdResidenzaClientiAbsFE("WRL_X_ITA") :
                        endDate == null ? this.recordService.selByIdResidenzaClientiAbsFE("WRL_X_ITA", startDate) :
                                this.recordService.selByIdResidenzaClientiAbsFE("WRL_X_ITA", startDate, endDate);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(recordDtoCompletoList), HttpStatus.OK);
    }

    @GetMapping("/abs/residenza/estero/{idProvincia}")
    public ResponseEntity<?> getByResidenzaEsteroAbsByProvincia(@Param("startDate") String startDate,
                                                                @Param("endDate") String endDate,
                                                                @PathVariable("idProvincia") String idProvincia) throws JsonProcessingException {
        List<RecordDtoCompleto> recordDtoCompletoList =
                startDate == null ? this.recordService.selByIdResidenzaClientiAndByIdProvinciaAbsFE("WRL_X_ITA", idProvincia) :
                        endDate == null ? this.recordService.selByIdResidenzaClientiAndByIdProvinciaAbsFE("WRL_X_ITA", startDate, idProvincia) :
                                this.recordService.selByIdResidenzaClientiAndByIdProvinciaAbsFE("WRL_X_ITA", startDate, endDate, idProvincia);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(recordDtoCompletoList), HttpStatus.OK);
    }

    @GetMapping("/abs")
    public ResponseEntity<?> getAbsoluteData(@Param("startDate") String startDate,
                                             @Param("endDate") String endDate) throws JsonProcessingException {
        List<RecordDtoCompleto> recordDtoCompletoList =
                startDate == null ? this.recordService.selAbsoluteData() :
                        endDate == null ? this.recordService.selAbsoluteData(startDate) :
                                this.recordService.selAbsoluteData(startDate, endDate);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(recordDtoCompletoList), HttpStatus.OK);
    }

    @GetMapping("/abs/{idProvincia}")
    public ResponseEntity<?> getAbsoluteDataByProvincia(@Param("startDate") String startDate,
                                                        @Param("endDate") String endDate,
                                                        @PathVariable("idProvincia") String idProvincia) throws JsonProcessingException {
        List<RecordDtoCompleto> recordDtoCompletoList =
                startDate == null ? this.recordService.selAbsoluteDataByIdProvincia(idProvincia) :
                        endDate == null ? this.recordService.selAbsoluteDataByIdProvincia(startDate, idProvincia) :
                                this.recordService.selAbsoluteDataByIdProvincia(startDate, endDate, idProvincia);
        return new ResponseEntity<>(this.objectMapper.writeValueAsString(recordDtoCompletoList), HttpStatus.OK);
    }

}
