package se.ifmo.ru.web.model;

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
@XmlType
@XmlRootElement(name = "Flats")
public class FlatsListGetResponseDto{
    private List<FlatGetResponseDto> flatGetResponseDtos;

    @XmlElement(name = "Flat")
    public List<FlatGetResponseDto> getFlatGetResponseDtos() {
        return flatGetResponseDtos;
    }

    public FlatsListGetResponseDto(List<FlatGetResponseDto> flatGetResponseDtos) {
        this.flatGetResponseDtos = flatGetResponseDtos;
    }
}
