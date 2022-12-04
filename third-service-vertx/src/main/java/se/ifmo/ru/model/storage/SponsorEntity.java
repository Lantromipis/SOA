package se.ifmo.ru.model.storage;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "sponsor")
@Data
public class SponsorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;
}
