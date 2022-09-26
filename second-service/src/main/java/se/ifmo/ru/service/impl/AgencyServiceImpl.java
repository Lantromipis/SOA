package se.ifmo.ru.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import se.ifmo.ru.exception.NotFoundException;
import se.ifmo.ru.external.client.CatalogRestClient;
import se.ifmo.ru.mapper.FlatMapper;
import se.ifmo.ru.service.model.Flat;
import se.ifmo.ru.service.api.AgencyService;

import java.util.List;

@Service
public class AgencyServiceImpl implements AgencyService {
    private CatalogRestClient catalogRestClient;
    private FlatMapper flatMapper;

    public AgencyServiceImpl(CatalogRestClient catalogRestClient, FlatMapper flatMapper) {
        this.catalogRestClient = catalogRestClient;
        this.flatMapper = flatMapper;
    }

    @Override
    public Flat getCheapest(long id1, long id2) {
        Flat flat1 = flatMapper.fromRestClient(catalogRestClient.getFlatById(id1));
        if (flat1 == null) {
            throw new NotFoundException("Flat with id " + id1 + " not found");
        }

        Flat flat2 = flatMapper.fromRestClient(catalogRestClient.getFlatById(id2));
        if (flat2 == null) {
            throw new NotFoundException("Flat with id " + id2 + " not found");
        }

        return flat1.getPrice() < flat2.getPrice() ? flat1 : flat2;
    }

    @Override
    public double getTotalCost() {
        List<Flat> flats = flatMapper.fromRestClient(catalogRestClient.getAllFlats());

        if (CollectionUtils.isNotEmpty(flats)) {
            int totalSum = 0;
            for (Flat flat : flats) {
                if (flat.getPrice() != null) {
                    totalSum += flat.getPrice();
                }
            }
            return totalSum;
        }

        return 0;
    }
}
