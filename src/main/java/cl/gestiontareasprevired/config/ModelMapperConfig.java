package cl.gestiontareasprevired.config;

import cl.gestiontareasprevired.dto.TareaDto;
import cl.gestiontareasprevired.model.Tarea;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.typeMap(Tarea.class, TareaDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getUsuario().getEmail(), TareaDto::setUsuarioEmail);
            mapper.map(src -> src.getEstado().getEstadoTarea(), TareaDto::setEstadoTarea);
        });

        return modelMapper;
    }
}