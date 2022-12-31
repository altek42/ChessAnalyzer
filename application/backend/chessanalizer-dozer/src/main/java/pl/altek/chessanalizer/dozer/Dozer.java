package pl.altek.chessanalizer.dozer;

import org.dozer.DozerBeanMapper;
import org.dozer.MappingException;

import java.util.ArrayList;
import java.util.List;

public class Dozer extends DozerBeanMapper {

    public <T> List<T> map(List<?> source, Class<T> destinationClass) throws MappingException {
        List<T> mapped = new ArrayList<>();
        for(Object e: source){
            T mapElement = this.map(e, destinationClass);
            mapped.add(mapElement);
        }
        return mapped;
    }

    public <T extends Enum<T>, S extends Enum<S>> T mapEnum(Enum<S> source, Class<T> destinationClass) {
        return Enum.valueOf(destinationClass, source.name());
    }

    public <T extends Enum<T>, S extends Enum<S>> List<T> mapEnum(List<S> source, Class<T> destinationClass) {
        List<T> mapped = new ArrayList<>();
        for(Enum<S> e: source){
            T mapElement = this.mapEnum(e, destinationClass);
            mapped.add(mapElement);
        }
        return mapped;
    }

}
