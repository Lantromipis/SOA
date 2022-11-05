package se.ifmo.ru.mapper;

import org.mapstruct.Mapper;
import se.ifmo.ru.ejb.model.Flat;
import se.ifmo.ru.ejb.model.Transport;
import se.ifmo.ru.web.model.FlatAddOrUpdateRequestDto;
import se.ifmo.ru.web.model.FlatGetResponseDto;

import java.util.List;

@Mapper
public interface FlatMapper {
    FlatGetResponseDto toDto(Flat source);

    List<FlatGetResponseDto> toGetResponseDtoList(List<Flat> source);

    Flat fromDto(FlatAddOrUpdateRequestDto dto);

    default Transport fromString(String string) {
        return Transport.fromValue(string);
    }
}
