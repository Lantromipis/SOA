package se.ifmo.ru.web.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlType
@XmlRootElement(name = "Coordinates")
public class CoordinatesDto {
    private Float x;
    private Integer y;
}