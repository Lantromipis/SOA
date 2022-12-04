package se.ifmo.ru.mapper;

import org.apache.commons.collections4.CollectionUtils;
import se.ifmo.ru.model.storage.CountryEntity;
import se.ifmo.ru.model.storage.HouseEntity;
import se.ifmo.ru.model.web.CountryListResponseDto;
import se.ifmo.ru.model.web.CountryResponseDto;
import se.ifmo.ru.model.web.HouseListResponseDto;
import se.ifmo.ru.model.web.HouseResponseDto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HouseMapper {
    public static HouseListResponseDto from(List<HouseEntity> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return HouseListResponseDto
                    .builder()
                    .houseResponseDtos(Collections.emptyList())
                    .build();
        }

        return HouseListResponseDto
                .builder()
                .houseResponseDtos(entities.stream().map(HouseMapper::from).collect(Collectors.toList()))
                .build();

    }

    public static HouseResponseDto from(HouseEntity source) {
        return HouseResponseDto
                .builder()
                .id(source.getId())
                .name(source.getName())
                .numberOfFloors(source.getNumberOfFloors())
                .numberOfLifts(source.getNumberOfLifts())
                .year(source.getYear())
                .build();
    }
}
