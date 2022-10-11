package se.ifmo.ru.service.impl;

import se.ifmo.ru.mapper.HouseMapper;
import se.ifmo.ru.service.api.HouseService;
import se.ifmo.ru.service.model.House;
import se.ifmo.ru.storage.repository.HouseRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class HouseServiceImpl implements HouseService {
    @Inject
    HouseRepository houseRepository;

    @Inject
    HouseMapper houseMapper;

    public List<House> getHousesInCountry(long countryId) {
        return houseMapper.fromEntityList(houseRepository.getHousesInCountry(countryId));
    }
}
