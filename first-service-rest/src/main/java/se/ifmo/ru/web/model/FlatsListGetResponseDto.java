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
    private Integer page;
    private Integer pageSize;
    private Integer totalPages;
    private Long totalCount;

    @XmlElement(name = "Flat")
    public List<FlatGetResponseDto> getFlatGetResponseDtos() {
        return flatGetResponseDtos;
    }

    public FlatsListGetResponseDto(List<FlatGetResponseDto> flatGetResponseDtos, Integer page, Integer pageSize, Integer totalPages, Long totalCount) {
        this.flatGetResponseDtos = flatGetResponseDtos;
        this.page = page;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.totalCount = totalCount;
    }
}
