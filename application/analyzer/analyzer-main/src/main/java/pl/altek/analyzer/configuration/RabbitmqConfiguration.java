package pl.altek.analyzer.configuration;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.altek.analyzer.common.RabbitQueue;

import javax.annotation.PostConstruct;

@Configuration
public class RabbitmqConfiguration {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @PostConstruct
    public void createQueues() {
        for (RabbitQueue queue : RabbitQueue.values()) {
            amqpAdmin.declareQueue(new Queue(queue.name(), true));
        }
    }

    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
