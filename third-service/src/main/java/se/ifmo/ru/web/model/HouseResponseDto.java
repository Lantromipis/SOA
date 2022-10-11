package se.ifmo.ru.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlType
@XmlRootElement(name = "House")
public class HouseResponseDto {
    private Long id;
    private String name;
    private Long year;
    private Long numberOfFloors;
    private Long numberOfLifts;
}
