package se.ifmo.ru.storage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.ifmo.ru.service.model.Transport;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flat")
public class FlatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "coordinates_x")
    private float coordinatesX;
    @Column(name = "coordinates_y")
    private Integer coordinatesY;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "area")
    private Double area;

    @Column(name = "number_of_rooms")
    private long numberOfRooms;

    @Column(name = "height")
    private Integer height;

    @Column(name = "new_field")
    private Boolean newField;

    @Column(name = "transport")
    @Enumerated(EnumType.STRING)
    private Transport transport;

    @Column(name = "house_name")
    private String houseName;
    @Column(name = "house_year")
    private Integer houseYear;
    @Column(name = "house_number_of_floors")
    private long houseNumberOfFloors;
    @Column(name = "house_number_of_lifts")
    private Long houseNumberOfLifts;

    @Column(name = "price")
    private Double price;
}
