package pl.altek.chessanalizer.db.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.neo4j.driver.Record;
import org.neo4j.driver.summary.ResultSummary;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Relationship;
import org.neo4j.driver.types.TypeSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Repository;
import pl.altek.chessanalizer.db.node.StateNode;
import pl.altek.chessanalizer.db.relation.MoveRelation;
import pl.altek.chessanalizer.dozer.Dozer;

import java.util.Collection;
import java.util.Map;

@Repository
public class TestRepositoryImpl implements TestRepository {

    @Autowired
    private Neo4jClient client;

    @Autowired
    private Dozer dozer;

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public void test() {
        Collection<MoveRelation> r2 = client.query("match (n)-[r:ENEMY_MOVE]-(n2) " +
                        "where n.hash='0056992a41fe7b3940d5cb4202ef682d' " +
                        "AND r.name='e4' " +
                        "SET r.quantity = r.quantity+1 " +
                        "return r, n2")
                .fetchAs(MoveRelation.class).mappedBy((TypeSystem t, Record record) -> {
                    Relationship r = record.get("r").asRelationship();
                    Node n = record.get("n2").asNode();

                    StateNode stateNode = objectMapper.convertValue(n.asMap(), StateNode.class);
                    MoveRelation move = objectMapper.convertValue(r.asMap(), MoveRelation.class);

                    move.setId(r.id());
                    move.setState(stateNode);
                    return move;
                }).all();
    }
}
