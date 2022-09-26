package se.ifmo.ru.storage.repository.api;

import se.ifmo.ru.storage.model.Filter;
import se.ifmo.ru.storage.model.FlatEntity;
import se.ifmo.ru.storage.model.Sort;

import java.util.List;

public interface FlatRepository {
    FlatEntity findById(long id);

    FlatEntity save(FlatEntity entity);

    boolean deleteById(long id);

    List<FlatEntity> getSortedAndFilteredPage(List<Sort> sortList, List<Filter> filters, Integer page, Integer size);

    long countHeight();

    FlatEntity getWithMaxId();

    long countByNewField(Boolean newField);
}
