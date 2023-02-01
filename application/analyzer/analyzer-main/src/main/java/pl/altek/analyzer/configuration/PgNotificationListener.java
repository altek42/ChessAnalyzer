package pl.altek.analyzer.configuration;

import com.impossibl.postgres.api.jdbc.PGConnection;
import com.impossibl.postgres.api.jdbc.PGNotificationListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import pl.altek.analyzer.common.RabbitQueue;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Statement;

@Slf4j
@Configuration
public class PgNotificationListener implements PGNotificationListener {

    private PGConnection pgConnection;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    private void initialize() throws Throwable {
        log.info("Setup postgres notification");

        this.pgConnection = (PGConnection) dataSource.getConnection();
        pgConnection.addNotificationListener(this);

        Statement statement = pgConnection.createStatement();
        statement.execute("LISTEN table_changed");
        statement.close();
    }

    @Override
    public void notification(int processId, String channelName, String payload) {
        rabbitTemplate.convertAndSend(RabbitQueue.Fields.PROPERTIES_CHANGED, "");
    }
}
