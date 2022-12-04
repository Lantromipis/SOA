package se.ifmo.ru.model.web;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlType
@JacksonXmlRootElement(localName = "House")
public class HouseResponseDto {
    private Long id;
    private String name;
    private Long year;
    private Long numberOfFloors;
    private Long numberOfLifts;
}
