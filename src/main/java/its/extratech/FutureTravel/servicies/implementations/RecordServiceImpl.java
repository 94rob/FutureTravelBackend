package its.extratech.FutureTravel.servicies.implementations;

import its.extratech.FutureTravel.dtos.response.RecordDtoCompleto;
import its.extratech.FutureTravel.dtos.response.RecordDtoPresenze;
import its.extratech.FutureTravel.entities.*;
import its.extratech.FutureTravel.entities.Record;
import its.extratech.FutureTravel.fetchIstatData.XMLIstatReader;
import its.extratech.FutureTravel.repositories.RecordRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RecordServiceImpl {

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

    // Metodi di ricerca dati

    public List<RecordDtoCompleto> selByIdResidenza(char tipoDato, String idResidenza, String startDate, String endDate) {
        List<Record> list = recordRepository.selByIdResidenzaClienti(idResidenza);
        List<RecordDtoCompleto> recordList = filterByTipoDatoAndReturnsRecordDtoCompleto(list , tipoDato);
        return this.filterByDate(recordList, startDate, endDate);
    }

    public List<RecordDtoCompleto> selByIdResidenzaByIdProvincia(char tipoDato, String idResidenza, String startDate, String endDate, String idProvincia) {
        List<Record> list = this.recordRepository.selByIdResidenzaClientiAndByIdProvincia(idResidenza, idProvincia);
        List<RecordDtoCompleto> recordList = filterByTipoDatoAndReturnsRecordDtoCompleto(list , tipoDato);
        return this.filterByDate(recordList, startDate, endDate);
    }

    public List<RecordDtoCompleto> selByIdResidenzaAndIdAlloggio(char tipoDato, String idResidenza, String idAlloggio, String startDate, String endDate){
        List<Record> list = recordRepository.selByIdResidenzaAndIdAlloggio(idResidenza, idAlloggio);
        List<RecordDtoCompleto> recordList = filterByTipoDatoAndReturnsRecordDtoCompleto(list , tipoDato);
        return this.filterByDate(recordList, startDate, endDate);
    }

    public List<RecordDtoCompleto> selByIdResidenzaAndIdAlloggioByIdProvincia(char tipoDato, String idResidenza, String idAlloggio, String startDate, String endDate, String idProvincia){
        List<Record> list = recordRepository.selByIdResidenzaAndIdAlloggioByProvincia(idResidenza, idAlloggio, idProvincia);
        List<RecordDtoCompleto> recordList = filterByTipoDatoAndReturnsRecordDtoCompleto(list , tipoDato);
        return this.filterByDate(recordList, startDate, endDate);
    }

    public List<RecordDtoCompleto> selByIdResidenzaAndAlloggioAll(char tipoDato, String idResidenza, String startDate, String endDate) {
        List<Record> list = this.collapseTipoAlloggio(this.recordRepository.selByIdResidenzaClienti(idResidenza));
        List<RecordDtoCompleto> recordList = filterByTipoDatoAndReturnsRecordDtoCompleto(list , tipoDato);
        return this.filterByDate(recordList, startDate, endDate);
    }

    public List<RecordDtoCompleto> selByIdResidenzaAndAlloggioAllByProvincia(char tipoDato, String idResidenza, String startDate, String endDate, String idProvincia) {
        List<Record> list = this.collapseTipoAlloggio(this.recordRepository.selByIdResidenzaClientiAndByIdProvincia(idResidenza, idProvincia));
        List<RecordDtoCompleto> recordList = filterByTipoDatoAndReturnsRecordDtoCompleto(list , tipoDato);
        return this.filterByDate(recordList, startDate, endDate);
    }

    public List<RecordDtoCompleto> selByIdResidenzaAll(char tipoDato, String startDate, String endDate) {
        List<Record> list = this.collapseResidenzeClienti(this.recordRepository.selAllOrderedByTimeAsc());
        List<RecordDtoCompleto> recordList = filterByTipoDatoAndReturnsRecordDtoCompleto(list , tipoDato);
        return this.filterByDate(recordList, startDate, endDate);
    }

    public List<RecordDtoCompleto> selByIdResidenzaAllByProvincia(char tipoDato, String startDate, String endDate, String idProvincia) {
        List<Record> list = this.collapseResidenzeClienti(this.recordRepository.selByIdProvincia(idProvincia));
        List<RecordDtoCompleto> recordList = filterByTipoDatoAndReturnsRecordDtoCompleto(list , tipoDato);
        return this.filterByDate(recordList, startDate, endDate);
    }

    public List<RecordDtoCompleto> selByIdTipoAlloggioAndIdResidenzaAll(char tipoDato, String idAlloggio, String startDate, String endDate) {
        List<Record> list = this.collapseResidenzeClienti(this.recordRepository.selByIdTipoAlloggio(idAlloggio));
        List<RecordDtoCompleto> recordList = filterByTipoDatoAndReturnsRecordDtoCompleto(list , tipoDato);
        return this.filterByDate(recordList, startDate, endDate);
    }

    public List<RecordDtoCompleto> selByIdTipoAlloggioAndIdResidenzaAllByProvincia(char tipoDato, String idAlloggio, String startDate, String endDate, String idProvincia) {
        List<Record> list = this.collapseResidenzeClienti(this.recordRepository.selByIdTipoAlloggioAndByIdProvincia(idAlloggio, idProvincia));
        List<RecordDtoCompleto> recordList = filterByTipoDatoAndReturnsRecordDtoCompleto(list , tipoDato);
        return this.filterByDate(recordList, startDate, endDate);
    }

    public List<RecordDtoCompleto> selByResidenzaAllAndAlloggioAll(char tipoDato, String startDate, String endDate){
        List<Record> list = this.collapseResidenzeClienti(this.collapseTipoAlloggio(this.recordRepository.selAllOrderedByTimeAsc()));
        List<RecordDtoCompleto> recordList = filterByTipoDatoAndReturnsRecordDtoCompleto(list , tipoDato);
        return this.filterByDate(recordList, startDate, endDate);
    }

    public List<RecordDtoCompleto> selByResidenzaAllAndAlloggioAllByProvincia(char tipoDato, String startDate, String endDate, String idProvincia){
        List<Record> list = this.collapseResidenzeClienti(this.collapseTipoAlloggio(this.recordRepository.selByIdProvincia(idProvincia)));
        List<RecordDtoCompleto> recordList = filterByTipoDatoAndReturnsRecordDtoCompleto(list , tipoDato);
        return this.filterByDate(recordList, startDate, endDate);
    }

    public List<RecordDtoCompleto> selAll(char tipoDato, String startDate, String endDate) {
        List<Record> list = this.recordRepository.selAllOrderedByTimeAsc();
        List<RecordDtoCompleto> recordList = filterByTipoDatoAndReturnsRecordDtoCompleto(list , tipoDato);
        return this.filterByDate(recordList, startDate, endDate);
    }

    public List<RecordDtoCompleto> selAllByProvincia(char tipoDato, String startDate, String endDate, String idProvincia) {
        List<Record> list = this.recordRepository.selByIdProvincia(idProvincia);
        List<RecordDtoCompleto> recordList = filterByTipoDatoAndReturnsRecordDtoCompleto(list , tipoDato);
        return this.filterByDate(recordList, startDate, endDate);
    }

    public List<RecordDtoCompleto> selByIdTipoAlloggio(char tipoDato, String idAlloggio, String startDate, String endDate) {
        List<Record> list = recordRepository.selByIdTipoAlloggio(idAlloggio);
        List<RecordDtoCompleto> recordList = filterByTipoDatoAndReturnsRecordDtoCompleto(list , tipoDato);
        return this.filterByDate(recordList, startDate, endDate);
    }

    public List<RecordDtoCompleto> selByIdTipoAlloggioByProvincia(char tipoDato, String idAlloggio, String startDate, String endDate, String idProvincia) {
        List<Record> list = recordRepository.selByIdTipoAlloggioAndByIdProvincia(idAlloggio, idProvincia);
        List<RecordDtoCompleto> recordList = filterByTipoDatoAndReturnsRecordDtoCompleto(list , tipoDato);
        return this.filterByDate(recordList, startDate, endDate);
    }

    public List<RecordDtoCompleto> selByIdAlloggioAll(char tipoDato, String startDate, String endDate) {
        List<Record> list = this.collapseTipoAlloggio(this.recordRepository.selAllOrderedByTimeAsc());
        List<RecordDtoCompleto> recordList = filterByTipoDatoAndReturnsRecordDtoCompleto(list , tipoDato);
        return this.filterByDate(recordList, startDate, endDate);
    }

    public List<RecordDtoCompleto> selByIdAlloggioAllByProvincia(char tipoDato, String startDate, String endDate, String idProvincia) {
        List<Record> list = this.collapseTipoAlloggio(this.recordRepository.selByIdProvincia(idProvincia));
        List<RecordDtoCompleto> recordList = filterByTipoDatoAndReturnsRecordDtoCompleto(list , tipoDato);
        return this.filterByDate(recordList, startDate, endDate);
    }

    public String getLastDate(){
        Record record = recordRepository.selAllOrderedByTimeDesc().get(0);
        return record.getTime();
    }

    // TODO eliminare FintechController e i metodi relativi?

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

    public List<RecordDtoPresenze> selByIdResidenza(String idResidenzaClienti) {
        return this.recordRepository.selByIdResidenzaClienti(idResidenzaClienti)
                .stream()
                .map(this::fromRecordToRecordDtoPresenze)
                .collect(Collectors.toList());
    }

    // Inserimento dati

    public void fetch(String date) throws IOException {
        XMLIstatReader xmlIstatReader = new XMLIstatReader();
        List<Record> recordList = xmlIstatReader.fetch(date);

        if (recordList == null){
            System.out.println("Nessun record da inserire");
            return;
        }

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
            r.setTipoDato('S');
            this.save(r);
        }
    }

    public void save(Record record) {
        this.recordRepository.save(record);
    }


    // Metodi interni

    private List<RecordDtoCompleto> filterByTipoDatoAndReturnsRecordDtoCompleto(List<Record> list, char tipoDato){
        return list
                .stream()
                .map(this::fromRecordToRecordDtoCompleto)
                .filter(r -> r.getTipoDato() == tipoDato)
                .collect(Collectors.toList());
    }

    private List<RecordDtoCompleto> filterByDate(List<RecordDtoCompleto> list, String startDate, String endDate){
        if ((startDate == null) && (endDate == null)){
            return list;
        }
        else if (startDate == null) {
            return list.
                    stream().
                    filter(r -> this.timeStringToInt(r.getTime()) <= this.timeStringToInt(endDate)).
                    collect(Collectors.toList());
        }
        else if (endDate == null){
            return list.
                    stream().
                    filter(r -> this.timeStringToInt(r.getTime()) >= this.timeStringToInt(startDate)).
                    collect(Collectors.toList());
        }
        else {
            return list.
                    stream().
                    filter(r -> (this.timeStringToInt(r.getTime()) >= this.timeStringToInt(startDate)
                            && this.timeStringToInt(r.getTime()) <= this.timeStringToInt(endDate))).
                    collect(Collectors.toList());
        }
    }

    private RecordDtoCompleto fromRecordToRecordDtoCompleto(Record record) {
        return modelMapper.map(record, RecordDtoCompleto.class);
    }

    private RecordDtoPresenze fromRecordToRecordDtoPresenze(Record record) {
        return modelMapper.map(record, RecordDtoPresenze.class);
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
                    recordList.remove(j);
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
                    recordList.remove(j);
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

    private int timeStringToInt(String time){
        return Integer.parseInt(time.replace("-", ""));
    }

}
