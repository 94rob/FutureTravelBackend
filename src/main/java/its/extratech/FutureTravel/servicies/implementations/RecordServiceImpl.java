package its.extratech.FutureTravel.servicies.implementations;

import its.extratech.FutureTravel.dtos.RecordDtoCompleto;
import its.extratech.FutureTravel.dtos.RecordDtoPresenze;
import its.extratech.FutureTravel.entities.*;
import its.extratech.FutureTravel.entities.Record;
import its.extratech.FutureTravel.importData.XMLIstatReader;
import its.extratech.FutureTravel.repositories.RecordRepository;
import its.extratech.FutureTravel.servicies.interfaces.RecordService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    ContestoServiceImpl contestoServiceImpl;

    @Autowired
    ProvinciaServiceImpl provinciaServiceImpl;

    @Autowired
    TipoAlloggioServiceImpl tipoAlloggioServiceImpl;

    @Autowired
    ResidenzaClientiServiceImpl residenzaClientiServiceImpl;

    @Autowired
    ModelMapper modelMapper;

    public List<RecordDtoCompleto> selByIdTipoAlloggioFE(String idAlloggio) {
        return this.recordRepository.selByIdTipoAlloggio(idAlloggio)
                .stream()
                .map(this::fromRecordToRecordDtoCompleto)
                .collect(Collectors.toList());
    }

    public List<RecordDtoCompleto> selByIdTipoAlloggioFE(String idAlloggio, String startDate, String endDate) {
        return this.recordRepository.selByIdTipoAlloggioBetweenTwoDates(idAlloggio, startDate, endDate)
                .stream()
                .map(this::fromRecordToRecordDtoCompleto)
                .collect(Collectors.toList());
    }

    public List<RecordDtoCompleto> selByIdTipoAlloggioFE(String idAlloggio, String startDate) {
        return this.recordRepository.selByIdTipoAlloggioSinceDate(idAlloggio, startDate)
                .stream()
                .map(this::fromRecordToRecordDtoCompleto)
                .collect(Collectors.toList());
    }

    public List<RecordDtoCompleto> selByIdTipoAlloggioAndByIdProvinciaFE(String idAlloggio, String idProvincia) {
        return this.recordRepository.selByIdTipoAlloggioAndByIdProvincia(idAlloggio, idProvincia)
                .stream()
                .map(this::fromRecordToRecordDtoCompleto)
                .collect(Collectors.toList());
    }

    public List<RecordDtoCompleto> selByIdTipoAlloggioAndByIdProvinciaFE(String idAlloggio, String startDate, String idProvincia) {
        return this.recordRepository.selByIdTipoAlloggioAndByIdProvinciaSinceDate(idAlloggio, startDate, idProvincia)
                .stream()
                .map(this::fromRecordToRecordDtoCompleto)
                .collect(Collectors.toList());
    }

    public List<RecordDtoCompleto> selByIdTipoAlloggioAndByIdProvinciaFE(String idAlloggio, String startDate, String endDate, String idProvincia) {
        return this.recordRepository.selByIdTipoAlloggioAndByIdProvinciaBetweenTwoDates(idAlloggio, startDate, endDate, idProvincia)
                .stream()
                .map(this::fromRecordToRecordDtoCompleto)
                .collect(Collectors.toList());
    }

    public List<RecordDtoCompleto> selByIdResidenzaClientiFE(String idResidenza) {
        return this.recordRepository.selByIdResidenzaClienti(idResidenza)
                .stream()
                .map(this::fromRecordToRecordDtoCompleto)
                .collect(Collectors.toList());
    }

    public List<RecordDtoCompleto> selByIdResidenzaClientiFE(String idResidenza, String startDate) {
        return this.recordRepository.selByIdResidenzaClientiSinceDate(idResidenza, startDate)
                .stream()
                .map(this::fromRecordToRecordDtoCompleto)
                .collect(Collectors.toList());
    }

    public List<RecordDtoCompleto> selByIdResidenzaClientiFE(String idResidenza, String startDate, String endDate) {
        return this.recordRepository.selByIdResidenzaClientiBetweenTwoDates(idResidenza, startDate, endDate)
                .stream()
                .map(this::fromRecordToRecordDtoCompleto)
                .collect(Collectors.toList());
    }

    public List<RecordDtoCompleto> selByIdResidenzaClientiAndByIdProvinciaFE(String idResidenza, String idProvincia) {
        return this.recordRepository.selByIdResidenzaClientiAndByIdProvincia(idResidenza, idProvincia)
                .stream()
                .map(this::fromRecordToRecordDtoCompleto)
                .collect(Collectors.toList());
    }

    public List<RecordDtoCompleto> selByIdResidenzaClientiAndByIdProvinciaFE(String idResidenza, String startDate, String idProvincia) {
        return this.recordRepository.selByIdResidenzaClientiAndByIdProvinciaSinceDate(idResidenza, startDate, idProvincia)
                .stream()
                .map(this::fromRecordToRecordDtoCompleto)
                .collect(Collectors.toList());
    }

    public List<RecordDtoCompleto> selByIdResidenzaClientiAndByIdProvinciaFE(String idResidenza, String startDate, String endDate, String idProvincia) {
        return this.recordRepository.selByIdResidenzaClientiAndByIdProvinciaBetweenTwoDates(idResidenza, startDate, endDate, idProvincia)
                .stream()
                .map(this::fromRecordToRecordDtoCompleto)
                .collect(Collectors.toList());
    }

    public List<RecordDtoCompleto> selByIdTipoAlloggioAbsFE(String idAlloggio) {
        return this.collapseResidenzeClienti(this.recordRepository.selByIdTipoAlloggio(idAlloggio))
                .stream()
                .map(this::fromRecordToRecordDtoCompleto)
                .collect(Collectors.toList());
    }

    public List<RecordDtoCompleto> selByIdTipoAlloggioAbsFE(String idAlloggio, String startDate) {
        return this.collapseResidenzeClienti(this.recordRepository.selByIdTipoAlloggioSinceDate(idAlloggio, startDate))
                .stream()
                .map(this::fromRecordToRecordDtoCompleto)
                .collect(Collectors.toList());
    }

    public List<RecordDtoCompleto> selByIdTipoAlloggioAbsFE(String idAlloggio, String startDate, String endDate) {
        return this.collapseResidenzeClienti(this.recordRepository.selByIdTipoAlloggioBetweenTwoDates(idAlloggio, startDate, endDate))
                .stream()
                .map(this::fromRecordToRecordDtoCompleto)
                .collect(Collectors.toList());
    }

    public List<RecordDtoCompleto> selByIdTipoAlloggioAndByIdProvinciaAbsFE(String idAlloggio, String idProvincia) {
        return this.collapseResidenzeClienti(this.recordRepository.selByIdTipoAlloggioAndByIdProvincia(idAlloggio, idProvincia))
                .stream()
                .map(this::fromRecordToRecordDtoCompleto)
                .collect(Collectors.toList());
    }

    public List<RecordDtoCompleto> selByIdTipoAlloggioAndByIdProvinciaAbsFE(String idAlloggio, String startDate, String idProvincia) {
        return this.collapseResidenzeClienti(this.recordRepository.selByIdTipoAlloggioAndByIdProvinciaSinceDate(idAlloggio, startDate, idProvincia))
                .stream()
                .map(this::fromRecordToRecordDtoCompleto)
                .collect(Collectors.toList());
    }

    public List<RecordDtoCompleto> selByIdTipoAlloggioAndByIdProvinciaAbsFE(String idAlloggio, String startDate, String endDate, String idProvincia) {
        return this.collapseResidenzeClienti(this.recordRepository.selByIdTipoAlloggioAndByIdProvinciaBetweenTwoDates(idAlloggio, startDate, endDate, idProvincia))
                .stream()
                .map(this::fromRecordToRecordDtoCompleto)
                .collect(Collectors.toList());
    }

    public List<RecordDtoCompleto> selByIdResidenzaClientiAbsFE(String idResidenza) {
        return this.collapseTipoAlloggio(this.recordRepository.selByIdResidenzaClienti(idResidenza))
                .stream()
                .map(this::fromRecordToRecordDtoCompleto)
                .collect(Collectors.toList());
    }

    public List<RecordDtoCompleto> selByIdResidenzaClientiAbsFE(String idResidenza, String startDate) {
        return this.collapseTipoAlloggio(this.recordRepository.selByIdResidenzaClientiSinceDate(idResidenza, startDate))
                .stream()
                .map(this::fromRecordToRecordDtoCompleto)
                .collect(Collectors.toList());
    }

    public List<RecordDtoCompleto> selByIdResidenzaClientiAbsFE(String idResidenza, String startDate, String endDate) {
        return this.collapseTipoAlloggio(this.recordRepository.selByIdResidenzaClientiBetweenTwoDates(idResidenza, startDate, endDate))
                .stream()
                .map(this::fromRecordToRecordDtoCompleto)
                .collect(Collectors.toList());
    }

    public List<RecordDtoCompleto> selByIdResidenzaClientiAndByIdProvinciaAbsFE(String idResidenza, String idProvincia) {
        return this.collapseTipoAlloggio(this.recordRepository.selByIdResidenzaClientiAndByIdProvincia(idResidenza, idProvincia))
                .stream()
                .map(this::fromRecordToRecordDtoCompleto)
                .collect(Collectors.toList());
    }

    public List<RecordDtoCompleto> selByIdResidenzaClientiAndByIdProvinciaAbsFE(String idResidenza, String startDate, String idProvincia) {
        return this.collapseTipoAlloggio(this.recordRepository.selByIdResidenzaClientiAndByIdProvinciaSinceDate(idResidenza, startDate, idProvincia))
                .stream()
                .map(this::fromRecordToRecordDtoCompleto)
                .collect(Collectors.toList());
    }

    public List<RecordDtoCompleto> selByIdResidenzaClientiAndByIdProvinciaAbsFE(String idResidenza, String startDate, String endDate, String idProvincia) {
        return this.collapseTipoAlloggio(this.recordRepository.selByIdResidenzaClientiAndByIdProvinciaBetweenTwoDates(idResidenza, startDate, endDate, idProvincia))
                .stream()
                .map(this::fromRecordToRecordDtoCompleto)
                .collect(Collectors.toList());
    }

    public List<RecordDtoPresenze> findAll() {
        return this.recordRepository.findAll()
                .stream()
                .map(this::fromRecordToRecordDtoPresenze)
                .collect(Collectors.toList());
    }

    public List<RecordDtoPresenze> findByTime(String time) {
        return this.recordRepository.findByTime(time)
                .stream()
                .map(this::fromRecordToRecordDtoPresenze)
                .collect(Collectors.toList());
    }

    public List<RecordDtoPresenze> selByIdProvincia(String idProvincia) {
        return this.recordRepository.selByIdProvincia(idProvincia)
                .stream()
                .map(this::fromRecordToRecordDtoPresenze)
                .collect(Collectors.toList());
    }

    public List<RecordDtoCompleto> selByNomeProvincia(String nomeProvincia) {
        return this.recordRepository.selByNomeProvincia(nomeProvincia)
                .stream()
                .map(this::fromRecordToRecordDtoCompleto)
                .collect(Collectors.toList());
    }

    public List<RecordDtoPresenze> selByIdTipoAlloggio(String idAlloggio) {
        return this.recordRepository.selByIdTipoAlloggio(idAlloggio)
                .stream()
                .map(this::fromRecordToRecordDtoPresenze)
                .collect(Collectors.toList());
    }

    public List<RecordDtoPresenze> selByIdResidenzaClienti(String idResidenzaClienti) {
        return this.recordRepository.selByIdResidenzaClienti(idResidenzaClienti)
                .stream()
                .map(this::fromRecordToRecordDtoPresenze)
                .collect(Collectors.toList());
    }

    public RecordDtoCompleto fromRecordToRecordDtoCompleto(Record record) {
        return modelMapper.map(record, RecordDtoCompleto.class);
    }

    public RecordDtoPresenze fromRecordToRecordDtoPresenze(Record record) {
        return modelMapper.map(record, RecordDtoPresenze.class);
    }

    public void save(Record record) {
        this.recordRepository.save(record);
    }

    public void fetch() throws IOException {
        XMLIstatReader xmlIstatReader = new XMLIstatReader();
        List<Record> recordList = xmlIstatReader.fetch();

        for (Record r : recordList) {
            Provincia provincia = this.provinciaServiceImpl
                    .findById(r.getContesto().getProvincia().getId());
            TipoAlloggio tipoAlloggio = this.tipoAlloggioServiceImpl
                    .findById(r.getContesto().getTipoAlloggio().getId());
            ResidenzaClienti residenzaClienti = this.residenzaClientiServiceImpl
                    .findById(r.getContesto().getResidenzaClienti().getId());
            Contesto contesto = this.contestoServiceImpl
                    .findByProvinciaAndTipoAlloggioAndResidenzaClienti(provincia, tipoAlloggio, residenzaClienti);

            r.setContesto(contesto);
            this.save(r);
        }
    }

    public List<Record> collapseResidenzeClienti(List<Record> recordList){
        List<Record> newList = new ArrayList<>();
        Record newR;

        for(int i = 0; i < recordList.size() - 1; i++){
            boolean flag = false;
            for(int j = i + 1; j < recordList.size(); j++){
                if((Objects.equals(recordList.get(i).getContesto().getTipoAlloggio().getId(), recordList.get(j).getContesto().getTipoAlloggio().getId())) &&
                        (Objects.equals(recordList.get(i).getContesto().getProvincia().getId(), recordList.get(j).getContesto().getProvincia().getId())) &&
                        (Objects.equals(recordList.get(i).getTime(), recordList.get(j).getTime()))){

                    newR = recordList.get(i);
                    newR.setPresenze(recordList.get(i).getPresenze() + recordList.get(j).getPresenze());
                    newR.setArrivi(recordList.get(i).getArrivi() + recordList.get(j).getArrivi());
                    newR.getContesto().getResidenzaClienti().setId("ALL");
                    newR.getContesto().getResidenzaClienti().setDescrizione("ALL");
                    newList.add(newR);
                    flag = true;
                }
            }
            if(!flag){
                newR = recordList.get(i);
                newList.add(newR);
            }
        }
        return newList;
    }

    public List<Record> collapseTipoAlloggio(List<Record> recordList){
        List<Record> newList = new ArrayList<>();
        Record newR;

        for(int i = 0; i < recordList.size() - 1; i++){
            boolean flag = false;
            for(int j = i + 1; j < recordList.size(); j++){
                if((Objects.equals(recordList.get(i).getContesto().getResidenzaClienti().getId(), recordList.get(j).getContesto().getResidenzaClienti().getId())) &&
                        (Objects.equals(recordList.get(i).getContesto().getProvincia().getId(), recordList.get(j).getContesto().getProvincia().getId())) &&
                        (Objects.equals(recordList.get(i).getTime(), recordList.get(j).getTime()))){

                    newR = recordList.get(i);
                    newR.setPresenze(recordList.get(i).getPresenze() + recordList.get(j).getPresenze());
                    newR.setArrivi(recordList.get(i).getArrivi() + recordList.get(j).getArrivi());
                    newR.getContesto().getTipoAlloggio().setId("ALL");
                    newR.getContesto().getTipoAlloggio().setDescrizione("ALL");
                    newList.add(newR);
                    flag = true;
                }
            }
            if(!flag){
                newR = recordList.get(i);
                newList.add(newR);
            }
        }
        return newList;
    }

}
