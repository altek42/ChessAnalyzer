package pl.altek.chessanalizer.dozer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DozerConfig {

    @Bean
    public Dozer dozerBeanMapper(){
        List<String> mappingFiles = new ArrayList<>();
        mappingFiles.add("dozer_configuration.xml");
        mappingFiles.add("dozer_mapping.xml");

        Dozer dozerBeanMapper = new Dozer();
        dozerBeanMapper.setMappingFiles(mappingFiles);
        return dozerBeanMapper;
    }
}
