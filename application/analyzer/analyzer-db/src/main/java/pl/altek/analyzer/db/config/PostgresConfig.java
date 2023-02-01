package pl.altek.analyzer.db.config;

import com.impossibl.postgres.jdbc.PGDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PostgresConfig {

    @Value("${spring.datasource.url}")
    private String databaseUrl;
    @Value("${spring.datasource.username}")
    private String databaseUsername;
    @Value("${spring.datasource.password}")
    private String databasePassword;

    @Bean
    public DataSource postgresDataSource() {
        PGDataSource pgDataSource = new PGDataSource();
        pgDataSource.setUrl(databaseUrl);
        pgDataSource.setUser(databaseUsername);
        pgDataSource.setPassword(databasePassword);
        return pgDataSource;
    }

}
