package se.ifmo.ru.model.storage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "country")
@Data
@JsonIgnoreProperties(value= {"houses"})
public class CountryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "country")
    private Set<HouseEntity> houses;
}
