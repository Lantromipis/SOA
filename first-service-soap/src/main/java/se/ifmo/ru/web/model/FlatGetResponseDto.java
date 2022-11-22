package se.ifmo.ru.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.ifmo.ru.service.model.House;

import javax.xml.bind.annotation.XmlElement;
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
    private CoordinatesDto coordinates;
    private Date creationDate;
    private Double area;
    private long numberOfRooms;
    private Integer height;
    private Boolean newField;
    private String transport;
    private HouseDto house;
    private Double price;

    @XmlElement(name = "new")
    public Boolean getNewField(){
        return newField;
    }
}

