package se.ifmo.ru.model.web;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlType
@JacksonXmlRootElement(localName = "Houses")
public class HouseListResponseDto {
    List<HouseResponseDto> houseResponseDtos;

    @JacksonXmlProperty(localName = "House")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<HouseResponseDto> getHouseResponseDtos() {
        return houseResponseDtos;
    }
}
