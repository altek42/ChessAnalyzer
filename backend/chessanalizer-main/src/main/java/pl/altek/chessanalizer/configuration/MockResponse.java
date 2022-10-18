package pl.altek.chessanalizer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.altek.chessanalizer.openapi.client.chesscomapi.model.Game;
import pl.altek.chessanalizer.openapi.client.chesscomapi.model.GameList;
import pl.altek.chessanalizer.openapi.client.chesscomapi.model.GamePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
public class MockResponse {

    @Bean("mockDbUserId")
    public UUID mockDbUserId() {
        return UUID.fromString("09014f5d-1f4b-4e7e-a64c-51d0938d3567");
    }
    @Bean("mockUserId")
    public UUID mockUserId() {
        return UUID.fromString("d15b424c-2726-11eb-9577-df5e0a04388a");
    }
}
