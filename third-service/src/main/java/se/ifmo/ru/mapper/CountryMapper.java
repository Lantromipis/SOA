package se.ifmo.ru.mapper;

import org.mapstruct.Mapper;
import se.ifmo.ru.service.model.Country;
import se.ifmo.ru.storage.model.CountryEntity;
import se.ifmo.ru.web.model.CountryResponseDto;

import java.util.List;

@Mapper
public interface CountryMapper {
    Country fromEntity(CountryEntity countryEntity);
    List<Country> fromEntityList(List<CountryEntity> countryEntityList);

    CountryEntity toEntity(Country country);
    List<CountryEntity> toEntityList(List<Country> countryList);

    CountryResponseDto toDto(Country country);
    List<CountryResponseDto> toDtoList(List<Country> country);

}
