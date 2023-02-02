package pl.altek.analyzer.db.domain.properties;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "properties", schema = "public", catalog = "analyzer")
public class PropertiesEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;
    @Basic
    @Column(name = "name", nullable = false, length = 32)
    private String name;
    @Basic
    @Column(name = "value", nullable = false, length = 64)
    private String value;
}
