package se.ifmo.ru.service.api;

import se.ifmo.ru.service.model.Flat;

public interface AgencyService {
    Flat getCheapest(long id1, long id2);

    double getTotalCost();
}
