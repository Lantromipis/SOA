package se.ifmo.ru.mapper;

import org.mapstruct.Mapper;
import se.ifmo.ru.service.model.Coordinates;
import se.ifmo.ru.service.model.Flat;
import se.ifmo.ru.service.model.House;
import se.ifmo.ru.service.model.Transport;
import se.ifmo.ru.storage.model.FlatEntity;
import se.ifmo.ru.web.model.FlatGetResponseDto;

import java.util.List;

@Mapper
public interface FlatMapper {
    FlatGetResponseDto toDto(Flat source);

    List<FlatGetResponseDto> toGetResponseDtoList(List<Flat> source);

    default Flat fromEntity(FlatEntity entity) {
        if (entity == null) {
            return null;
        }

        Flat.FlatBuilder flat = Flat.builder();

        flat.id(entity.getId());
        flat.name(entity.getName());
        flat.coordinates(Coordinates
                .builder()
                .x(entity.getCoordinatesX())
                .y(entity.getCoordinatesY())
                .build()
        );
        flat.creationDate(entity.getCreationDate());
        flat.area(entity.getArea());
        flat.numberOfRooms(entity.getNumberOfRooms());
        flat.height(entity.getHeight());
        flat.newField(entity.getNewField());
        flat.transport(entity.getTransport());
        flat.house(House
                .builder()
                .name(entity.getHouseName())
                .year(entity.getHouseYear())
                .numberOfLifts(entity.getHouseNumberOfLifts())
                .numberOfFloors(entity.getHouseNumberOfFloors())
                .build()
        );
        flat.price(entity.getPrice());

        return flat.build();
    }

    List<Flat> fromEntityList(List<FlatEntity> entity);

    default String from(Transport transport) {
        return transport.toString();
    }
}
