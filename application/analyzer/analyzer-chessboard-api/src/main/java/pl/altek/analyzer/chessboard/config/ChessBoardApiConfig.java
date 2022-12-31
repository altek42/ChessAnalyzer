package pl.altek.analyzer.chessboard.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.altek.analyzer.openapi.client.chessboardapi.ApiClient;
import pl.altek.analyzer.openapi.client.chessboardapi.api.ChessApi;

@Configuration
public class ChessBoardApiConfig {

    @Value("${api.chessboard.url}")
    private String apiUrl;

    @Bean
    ApiClient chessBoardApiClient(@Autowired RestTemplate restTemplate) {
        ApiClient apiClient = new ApiClient(restTemplate);
        apiClient.setBasePath(apiUrl);
        return apiClient;
    }

    @Bean
    ChessApi chessApi(ApiClient chessBoardApiClient) {
        return new ChessApi(chessBoardApiClient);
    }
}
