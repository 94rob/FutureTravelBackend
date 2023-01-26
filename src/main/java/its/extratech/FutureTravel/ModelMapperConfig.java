package its.extratech.FutureTravel;

import its.extratech.FutureTravel.dtos.response.RecordDto;
import its.extratech.FutureTravel.dtos.response.RecordDtoFintech;
import its.extratech.FutureTravel.entities.Record;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setSkipNullEnabled(true);
        modelMapper.addMappings(recordToRecordDtoMapping);
        modelMapper.addMappings(recordToRecordDtoFintechMapping);

        return modelMapper;
    }

    PropertyMap<Record, RecordDto> recordToRecordDtoMapping = new PropertyMap<>() {
        protected void configure() {
            map().setProvincia(source.getContesto().getProvincia().getId());
            map().setTipoAlloggio(source.getContesto().getTipoAlloggio().getId());
            map().setResidenzaClienti(source.getContesto().getResidenzaClienti().getId());
        }
    };

    PropertyMap<Record, RecordDtoFintech> recordToRecordDtoFintechMapping = new PropertyMap<>() {
        protected void configure() {
            map().setITTER107(source.getContesto().getProvincia().getId());
            map().setTIPO_ALLOGGIO2(source.getContesto().getTipoAlloggio().getId());
            map().setISO(source.getContesto().getResidenzaClienti().getId());
            map().setTIME(source.getTime());
            map().setIndicatori("presenze");
            map().setValue(Integer.toString(source.getPresenze()));
        }
    };



}
