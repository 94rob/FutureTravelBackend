package its.extratech.FutureTravel;

import its.extratech.FutureTravel.dtos.RecordDtoCompleto;
import its.extratech.FutureTravel.dtos.RecordDtoPresenze;
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
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.addMappings(recordPresenzeMapping);
        modelMapper.addMappings(recordCompletoMapping);

        return modelMapper;
    }

    PropertyMap<Record, RecordDtoPresenze> recordPresenzeMapping = new PropertyMap<>() {
        protected void configure() {
            map().setProvincia(source.getContesto().getProvincia().getId());
            map().setTipoAlloggio(source.getContesto().getTipoAlloggio().getId());
            map().setResidenzaClienti(source.getContesto().getResidenzaClienti().getId());
        }
    };

    PropertyMap<Record, RecordDtoCompleto> recordCompletoMapping = new PropertyMap<>() {
        protected void configure() {
            map().setProvincia(source.getContesto().getProvincia().getId());
            map().setTipoAlloggio(source.getContesto().getTipoAlloggio().getId());
            map().setResidenzaClienti(source.getContesto().getResidenzaClienti().getId());
            map().setTipodato(source.getTipodato());
        }
    };
}
