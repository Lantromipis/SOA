package se.ifmo.ru.mapper;

import org.mapstruct.Mapper;
import se.ifmo.ru.external.model.RestClientFlat;
import se.ifmo.ru.service.model.Flat;
import se.ifmo.ru.service.model.Transport;
import se.ifmo.ru.web.model.FlatGetResponseDto;

import java.util.List;

@Mapper
public interface FlatMapper {
    FlatGetResponseDto toDto(Flat source);

    List<FlatGetResponseDto> toGetResponseDtoList(List<Flat> source);

    Flat fromRestClient(RestClientFlat restClientFlat);

    List<Flat> fromRestClient(List<RestClientFlat> restClientFlat);

    default String from(Transport transport) {
        return transport.toString();
    }

    default Transport from(String transport) {
        return Transport.fromValue(transport);
    }
}
