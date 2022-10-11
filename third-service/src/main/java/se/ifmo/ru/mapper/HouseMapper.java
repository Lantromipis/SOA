package se.ifmo.ru.mapper;

import org.mapstruct.Mapper;
import se.ifmo.ru.service.model.Country;
import se.ifmo.ru.service.model.House;
import se.ifmo.ru.storage.model.HouseEntity;
import se.ifmo.ru.web.model.CountryResponseDto;
import se.ifmo.ru.web.model.HouseListResponseDto;
import se.ifmo.ru.web.model.HouseResponseDto;

import java.util.List;

@Mapper
public interface HouseMapper {
    House fromEntity(HouseEntity houseEntity);
    List<House> fromEntityList(List<HouseEntity> houseEntityList);

    HouseEntity toEntity(House house);
    List<HouseEntity> toEntityList(List<House> houseList);

    HouseListResponseDto toDto(House house);
    List<HouseResponseDto> toDtoList(List<House> houseList);
}
