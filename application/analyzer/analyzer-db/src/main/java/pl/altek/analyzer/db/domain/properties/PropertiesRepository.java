package pl.altek.analyzer.db.domain.properties;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PropertiesRepository extends JpaRepository<PropertiesEntity, UUID> {
}
