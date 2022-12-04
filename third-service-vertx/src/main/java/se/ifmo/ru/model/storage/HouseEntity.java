package se.ifmo.ru.model.storage;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "house")
@Data
public class HouseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private long year;

    @Column(name = "number_of_floors")
    private long numberOfFloors;

    @Column(name = "number_of_lifts")
    private long numberOfLifts;

    @Column(name = "donated")
    private boolean donated;

    @OneToOne
    @JoinColumn(name = "sponsor_id")
    private SponsorEntity sponsor;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private CountryEntity country;
}
