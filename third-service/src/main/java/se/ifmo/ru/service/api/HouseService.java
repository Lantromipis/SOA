package se.ifmo.ru.service.api;

import se.ifmo.ru.service.model.House;

import java.util.List;

public interface HouseService {
    List<House> getHousesInCountry(long countryId);
}
