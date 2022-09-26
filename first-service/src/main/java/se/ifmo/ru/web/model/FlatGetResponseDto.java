package se.ifmo.ru.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlType
@XmlRootElement(name = "Flat")
public class FlatGetResponseDto {
    private long id;
    private String name;
    private FlatCoordinatesGetResponseDto coordinates;
    private Date creationDate;
    private Double area;
    private long numberOfRooms;
    private Integer height;
    private Boolean newField;
    private String transport;
    private FlatHouseGetResponseDto house;
    private Double price;

    @Data
    @XmlType
    @XmlRootElement(name = "Coordinates")
    public static class FlatCoordinatesGetResponseDto {
        private float x;
        private Integer y;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @XmlType
    @XmlRootElement(name = "House")
    public static class FlatHouseGetResponseDto {
        private String name;
        private Integer year;
        private long numberOfFloors;
        private Long numberOfLifts;
    }
}

