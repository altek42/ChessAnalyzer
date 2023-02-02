package pl.altek.analyzer.module.property;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.altek.analyzer.common.RabbitQueue;
import pl.altek.analyzer.db.domain.properties.PropertiesEntity;
import pl.altek.analyzer.db.domain.properties.PropertiesRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class PropertyService {

    @Autowired
    private PropertiesRepository propertiesRepository;

    @Autowired
    private pl.altek.analyzer.openapi.client.chesscomapi.ApiClient chesscomapiClient;

    @Autowired
    private pl.altek.analyzer.openapi.client.chessboardapi.ApiClient chessboardapiClient;

    private static Map<Property, String> propertyMap;

    @PostConstruct
    @RabbitListener(queues = RabbitQueue.Fields.PROPERTIES_CHANGED)
    private void parametersChanges() {
        updateProperty();
        updateApiUrl();
    }

    public static String getProperty(Property property) {
        return propertyMap.get(property);
    }

    private void updateProperty() {
        Map<Property, String> propertyMap = new ConcurrentHashMap<>();
        List<PropertiesEntity> propertiesList = propertiesRepository.findAll();
        propertiesList.forEach(entity -> addProperty(entity, propertyMap));
        PropertyService.propertyMap = propertyMap;
    }

    private static void addProperty(PropertiesEntity entity, Map<Property, String> propertyMap) {
        String name = entity.getName();
        Optional<Property> property = Property.optionalValueOf(name);
        property.ifPresent(x -> propertyMap.put(x, entity.getValue()));
    }

    private void updateApiUrl() {
        chessboardapiClient.setBasePath(getProperty(Property.API_CHESSBOARD_URL));
        chesscomapiClient.setBasePath(getProperty(Property.API_CHESSCOM_URL));
    }

}
