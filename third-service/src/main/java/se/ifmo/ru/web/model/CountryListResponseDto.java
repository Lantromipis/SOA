package se.ifmo.ru.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlType
@XmlRootElement(name = "Countries")
public class CountryListResponseDto {
    List<CountryResponseDto> countryResponseDtos;

    @XmlElement(name = "Country")
    public List<CountryResponseDto> getCountryResponseDtos() {
        return countryResponseDtos;
    }
}
