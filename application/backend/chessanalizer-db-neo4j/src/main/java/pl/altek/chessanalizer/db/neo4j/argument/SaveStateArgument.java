package pl.altek.chessanalizer.db.neo4j.argument;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.altek.chessanalizer.db.neo4j.model.Move;
import pl.altek.chessanalizer.db.neo4j.model.State;

@Data
@AllArgsConstructor
public class SaveStateArgument {
    private State source;
    private State destination;
    private Move relation;
}
