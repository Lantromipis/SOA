package se.ifmo.ru.web.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlType
@XmlRootElement(name = "Flat")
public class FlatAddOrUpdateRequestDto {
    private String name;
    private FlatCoordinatesAddResponseDto coordinates;
    private Double area;
    private long numberOfRooms;
    private Integer height;
    private Boolean newField;
    private String transport;
    private FlatHouseAddResponseDto house;
    private Double price;

    @Data
    @XmlType
    @XmlRootElement(name = "coordinates")
    public static class FlatCoordinatesAddResponseDto {
        private float x;
        private Integer y;
    }

    @Data
    @XmlType
    @XmlRootElement(name = "house")
    public static class FlatHouseAddResponseDto {
        private String name;
        private Integer year;
        private long numberOfFloors;
        private Long numberOfLifts;
    }
}
