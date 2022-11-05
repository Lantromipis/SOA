package se.ifmo.ru.ejb.api;

import se.ifmo.ru.ejb.model.Flat;
import se.ifmo.ru.ejb.model.Page;

import java.util.List;

public interface FlatService {
    Page<Flat> getFlats(List<String> sortsList, List<String> filtersList, Integer page, Integer pageSize);

    Flat getFlat(long id);

    Flat updateFlat(long id, Flat flat);

    Flat addFlat(Flat flat);

    boolean deleteFlat(long id);

    Flat getWithMaxId();

    long countByNew(Boolean newValue);

    long sumHeight();
}
