package pl.altek.chessanalizer.chesscom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.altek.chessanalizer.openapi.client.chesscomapi.ApiClient;
import pl.altek.chessanalizer.openapi.client.chesscomapi.api.PlayerApi;

@Configuration
public class ChessComApiConfig {

    @Value("${api.chesscom.url}")
    private String apiUrl;

    @Bean
    ApiClient chessComApiClient(@Autowired RestTemplate restTemplate){
        ApiClient apiClient = new ApiClient(restTemplate);
        apiClient.setBasePath(apiUrl);
        return apiClient;
    }

    @Bean
    PlayerApi playerApi(ApiClient chessComApiClient){
        return new PlayerApi(chessComApiClient);
    }
}
