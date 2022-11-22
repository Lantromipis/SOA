package se.ifmo.ru.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import se.ifmo.ru.ejb.model.Transport;
import se.ifmo.ru.soap.api.Flat;
import se.ifmo.ru.web.model.FlatAddOrUpdateRequestDto;
import se.ifmo.ru.web.model.FlatGetResponseDto;

import java.util.List;

@Mapper
public interface FlatMapper {
    @Mapping(target="newField", source="source.new")
    FlatGetResponseDto toDto(se.ifmo.ru.soap.api.FlatGetResponseDto source);

    List<FlatGetResponseDto> toGetResponseDtoList(List<se.ifmo.ru.soap.api.FlatGetResponseDto> source);

    @Mapping(target="new", source="dto.newField")
    Flat fromDto(FlatAddOrUpdateRequestDto dto);

    default Transport fromString(String string) {
        return Transport.fromValue(string);
    }
}
