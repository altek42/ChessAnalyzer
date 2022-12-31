package pl.altek.analyzer.db.neo4j.repository;

import pl.altek.analyzer.db.neo4j.argument.SaveStateArgument;

public interface StateRepository {
    void saveMoveRelation(SaveStateArgument argument);
}
