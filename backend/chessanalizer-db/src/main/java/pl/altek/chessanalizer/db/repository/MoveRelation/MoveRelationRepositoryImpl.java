package pl.altek.chessanalizer.db.repository.MoveRelation;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.Record;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Relationship;
import org.neo4j.driver.types.TypeSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Repository;
import pl.altek.chessanalizer.db.constants.MoveRelationType;
import pl.altek.chessanalizer.db.node.StateNode;
import pl.altek.chessanalizer.db.relation.MoveRelation;

import java.util.Collection;
import java.util.Optional;

@Repository
@Slf4j
class MoveRelationRepositoryImpl implements MoveRelationRepository {

    @Autowired
    private Neo4jClient client;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void save(StateNode sourceNode, MoveRelation moveRelation, MoveRelationType relationType) {
        StateNode distNode = moveRelation.getState();
        client.query("MATCH (s:State) " +
                        "WITH s " +
                        "MATCH (d:State) " +
                        "WHERE s.hash='"+sourceNode.getHash()+"' " +
                        "AND d.hash='"+distNode.getHash()+"' " +
                        "CREATE (s)-[r:" + relationType.getValue() +" " +
                        "{name:'"+ moveRelation.getName() +"', " +
                        "quantity:"+ moveRelation.getQuantity() +", " +
                        "userId:'"+moveRelation.getUserId()+"' } " +
                        "]->(d)")
                .fetch().one();
    }

    @Override
    public Optional<MoveRelation> findAndIncreaseQuantity(String sourceNodeHash, String name, MoveRelationType relationType) {
        Collection<MoveRelation> result = client
                .query(createSqlFindAndIncreaseQuantity(sourceNodeHash,name, relationType))
                .fetchAs(MoveRelation.class)
                .mappedBy(this::mapResult)
                .all();
        int size = result.size();
        if(size < 1){
            return Optional.empty();
        }
        if(size > 1){
            log.warn("For node '"+sourceNodeHash+"' is more moves relation with name '"+name+"'");
        }
        return Optional.of(result.iterator().next());
    }

    private String createSqlFindAndIncreaseQuantity(String sourceNodeHash, String name, MoveRelationType relationType) {
        return "match (n)-[r:" + relationType.getValue() + "]-(n2) " +
                "where n.hash='" + sourceNodeHash + "' " +
                "AND r.name='" + name + "' " +
                "SET r.quantity = r.quantity+1 " +
                "return r, n2";
    }

    private MoveRelation mapResult(TypeSystem t, Record record) {
        Relationship r = record.get("r").asRelationship();
        Node n = record.get("n2").asNode();

        MoveRelation move = objectMapper.convertValue(r.asMap(), MoveRelation.class);
        StateNode stateNode = objectMapper.convertValue(n.asMap(), StateNode.class);

        move.setId(r.id());
        move.setState(stateNode);
        return move;
    }
}
