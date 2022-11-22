package se.ifmo.ru.web.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlType(name = "Flat")
@XmlRootElement(name = "Flat")
public class FlatAddOrUpdateRequestDto {
    private String name;
    private CoordinatesDto coordinates;
    private Double area;
    private Long numberOfRooms;
    private Integer height;
    private Boolean newField;
    private String transport;
    private HouseDto house;
    private Double price;

    public Boolean getNewField() {
        return newField;
    }

    @XmlElement(name = "new")
    public void setNewField(Boolean newField) {
        this.newField = newField;
    }
}
