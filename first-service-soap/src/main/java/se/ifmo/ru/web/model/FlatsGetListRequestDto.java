package se.ifmo.ru.web.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlType
@XmlRootElement(name = "FlatPage")
public class FlatsGetListRequestDto {
    private List<String> sort;
    private List<String> filter;
    private Integer page;
    private Integer size;
}
