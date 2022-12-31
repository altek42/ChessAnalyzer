package pl.altek.chessanalizer.db.neo4j.model;

import lombok.Data;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Data
public class State {
    public State(String fen) {
        this.fen = fen;
        this.hash = DigestUtils.md5DigestAsHex(fen.getBytes(StandardCharsets.UTF_8));
    }

    private String hash;
    private String fen;
}
