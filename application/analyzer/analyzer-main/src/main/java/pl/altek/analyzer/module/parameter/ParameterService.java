package pl.altek.analyzer.module.parameter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import pl.altek.analyzer.common.RabbitQueue;

@Slf4j
@Service
public class ParameterService {

    @RabbitListener(queues = RabbitQueue.Fields.PROPERTIES_CHANGED)
    private void parametersChanges() {
        log.warn("parametersChanges!");
    }
}
