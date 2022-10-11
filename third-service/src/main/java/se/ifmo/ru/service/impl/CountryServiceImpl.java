package se.ifmo.ru.service.impl;

import se.ifmo.ru.mapper.CountryMapper;
import se.ifmo.ru.service.api.CountryService;
import se.ifmo.ru.service.model.Country;
import se.ifmo.ru.storage.repository.CountryRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class CountryServiceImpl implements CountryService {
    @Inject
    CountryMapper countryMapper;

    @Inject
    CountryRepository countryRepository;

    public List<Country> getCountries() {
        return countryMapper.fromEntityList(countryRepository.listAll());
    }
}
