package its.extratech.FutureTravel.servicies.implementations;

import its.extratech.FutureTravel.dtos.response.RecordDto;
import its.extratech.FutureTravel.entities.*;
import its.extratech.FutureTravel.entities.Record;
import its.extratech.FutureTravel.fetchIstatData.XMLIstatReader;
import its.extratech.FutureTravel.repositories.RecordRepository;
import its.extratech.FutureTravel.servicies.interfaces.IRecordService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RecordServiceImpl implements IRecordService {

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

    public List<RecordDto> getByResidenzaAndAlloggio(char tipoDato, String idResidenza, String idAlloggio, String startDate, String endDate){
        List<Record> list;
        List<RecordDto> recordList;

        // residenza whatever , alloggio whatever
        if((Objects.equals(idResidenza, "whatever")) && (Objects.equals(idAlloggio, "whatever"))) {
            list = recordRepository.selAllOrderedByTimeAsc();
        }

        // residenza whatever
        else if((Objects.equals(idResidenza, "whatever")) && (!Objects.equals(idAlloggio, "whatever"))) {

            list = Objects.equals(idAlloggio, "ALL")
                    ?
                    collapseTipoAlloggio(recordRepository.selAllOrderedByTimeAsc())
                    :
                    recordRepository.selByIdAlloggio(idAlloggio);
        }

        // alloggio whatever
        else if((!Objects.equals(idResidenza, "whatever")) && (Objects.equals(idAlloggio, "whatever"))) {
            list = (Objects.equals(idResidenza, "ALL"))
                    ?
                    collapseResidenzeClienti(recordRepository.selAllOrderedByTimeAsc())
                    :
                    recordRepository.selByIdResidenza(idResidenza);
        }

        else {
            if((Objects.equals(idAlloggio, "ALL")) && (Objects.equals(idResidenza, "ALL"))){
                list = collapseResidenzeClienti(collapseTipoAlloggio(recordRepository.selAllOrderedByTimeAsc()));
            }

            else if (Objects.equals(idAlloggio, "ALL")){
                list = collapseTipoAlloggio(recordRepository.selByIdResidenza(idResidenza));
            }

            else if (Objects.equals(idResidenza, "ALL")){
                list = collapseResidenzeClienti(recordRepository.selByIdAlloggio(idAlloggio));
            }

            else {
                list = recordRepository.selByIdResidenzaAndIdAlloggio(idResidenza, idAlloggio);
            }
        }

        recordList = filterByTipoDatoAndReturnsRecordDto(list, tipoDato);
        return this.filterByDate(recordList, startDate, endDate);
    }

    public List<RecordDto> getByResidenzaAndAlloggioByProvincia(char tipoDato, String idResidenza, String idAlloggio, String idProvincia, String startDate, String endDate){
        List<Record> list;
        List<RecordDto> recordList;

        // residenza whatever , alloggio whatever
        if((Objects.equals(idResidenza, "whatever")) && (Objects.equals(idAlloggio, "whatever"))) {
            list = recordRepository.selByIdProvincia(idProvincia);
        }

        // residenza whatever
        else if((Objects.equals(idResidenza, "whatever")) && (!Objects.equals(idAlloggio, "whatever"))) {

            list = (Objects.equals(idAlloggio, "ALL"))
                    ?
                    collapseTipoAlloggio(recordRepository.selByIdProvincia(idProvincia))
                    :
                    recordRepository.selByIdAlloggioAndByIdProvincia(idAlloggio, idProvincia);
        }

        // alloggio whatever
        else if((!Objects.equals(idResidenza, "whatever")) && (Objects.equals(idAlloggio, "whatever"))) {
            list = (Objects.equals(idResidenza, "ALL"))
                    ?
                    collapseResidenzeClienti(recordRepository.selByIdProvincia(idProvincia))
                    :
                    recordRepository.selByIdResidenzaAndByIdProvincia(idResidenza, idProvincia);
        }

        else {
            if((Objects.equals(idAlloggio, "ALL")) && (Objects.equals(idResidenza, "ALL"))){
                list = collapseResidenzeClienti(collapseTipoAlloggio(recordRepository.selByIdProvincia(idProvincia)));
            }

            else if (Objects.equals(idAlloggio, "ALL")){
                list = collapseTipoAlloggio(recordRepository.selByIdResidenzaAndByIdProvincia(idResidenza, idProvincia));
            }

            else if (Objects.equals(idResidenza, "ALL")){
                list = collapseResidenzeClienti(recordRepository.selByIdAlloggioAndByIdProvincia(idAlloggio, idProvincia));
            }

            else {
                list = recordRepository.selByIdResidenzaAndIdAlloggioByProvincia(idResidenza, idAlloggio, idProvincia);
            }
        }

        recordList = filterByTipoDatoAndReturnsRecordDto(list, tipoDato);
        return this.filterByDate(recordList, startDate, endDate);
    }

    public List<RecordDto> getAllByTipoDato(char tipoDato){
        List<Record> list = this.recordRepository.selAllOrderedByTimeAsc();
        return filterByTipoDatoAndReturnsRecordDto(list , tipoDato);
    }

    public String getLastDateRegistered(){
        try{
            Record record = recordRepository.selAllOrderedByTimeDescWhereTipodatoLikeS().get(0);
            return record.getTime();
        } catch (Exception ex){
            return "";
        }

    }


    // Metodi di inserimento dati

    public boolean fetchIstatApiAndSaveData(String date) throws IOException {
        XMLIstatReader xmlIstatReader = new XMLIstatReader();
        List<Record> recordList = xmlIstatReader.fetch(date);

        if ((recordList == null)||(recordList.isEmpty())){
            System.out.println("Nessun record da inserire");
            return false;
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
        return true;
    }

    public void save(Record record) {
        this.recordRepository.save(record);
    }


    // Metodi interni

    private List<RecordDto> filterByTipoDatoAndReturnsRecordDto(List<Record> list, char tipoDato){
        return list
                .stream()
                .map(this::fromRecordToRecordDto)
                .filter(r -> r.getTipoDato() == tipoDato)
                .collect(Collectors.toList());
    }

    private List<RecordDto> filterByDate(List<RecordDto> list, String startDate, String endDate){
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

    private RecordDto fromRecordToRecordDto(Record record) {
        return modelMapper.map(record, RecordDto.class);
    }

    private int timeStringToInt(String time){
        return Integer.parseInt(time.replace("-", ""));
    }

    // --
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

}
