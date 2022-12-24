package pl.altek.chessanalizer.db.neo4j.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Repository;
import pl.altek.chessanalizer.db.neo4j.argument.SaveStateArgument;
import pl.altek.chessanalizer.db.neo4j.model.Move;
import pl.altek.chessanalizer.db.neo4j.model.State;

@Slf4j
@Repository
class StateRepositoryImpl implements StateRepository {
    @Autowired
    private Neo4jClient client;

    @Override
    public void saveMoveRelation(SaveStateArgument argument) {
        State source = argument.getSource();
        State destination = argument.getDestination();
        Move relation = argument.getRelation();

        String query = "MATCH (s:STATE) where s.hash = '" + source.getHash() + "' " +
                "MERGE (d:STATE {hash: '" + destination.getHash() + "'}) " +
                "ON CREATE SET d.fen = '" + destination.getFen() + "' " +
                "MERGE (s) - [r:MOVE_" + relation.getType() + " {" +
                "userId: '" + relation.getUserId() + "', " +
                "name: '" + relation.getName() + "'" +
                "}] -> (d) " +
                "ON CREATE SET " +
                "r.quantity = 1, " +
                "r.win = " + relation.getWin() + ", " +
                "r.draw = " + relation.getDraw() + ", " +
                "r.lose = " + relation.getLose() + " " +
                "ON MATCH SET " +
                "r.quantity = r.quantity + 1, " +
                "r.win = r.win + " + relation.getWin() + ", " +
                "r.draw = r.draw + " + relation.getDraw() + ", " +
                "r.lose = r.lose + " + relation.getLose();

        client.query(query).fetch().one();
    }
}


