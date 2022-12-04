package se.ifmo.ru.mapper;

import org.apache.commons.collections4.CollectionUtils;
import se.ifmo.ru.model.storage.CountryEntity;
import se.ifmo.ru.model.web.CountryListResponseDto;
import se.ifmo.ru.model.web.CountryResponseDto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CountryMapper {
    public static CountryListResponseDto from(List<CountryEntity> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return CountryListResponseDto
                    .builder()
                    .countryResponseDtos(Collections.emptyList())
                    .build();
        }

        return CountryListResponseDto
                .builder()
                .countryResponseDtos(entities.stream().map(CountryMapper::from).collect(Collectors.toList()))
                .build();

    }

    public static CountryResponseDto from(CountryEntity source) {
        return CountryResponseDto
                .builder()
                .id(source.getId())
                .name(source.getName())
                .build();
    }
}
