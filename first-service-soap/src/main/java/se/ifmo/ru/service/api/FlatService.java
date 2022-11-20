package se.ifmo.ru.service.api;

import se.ifmo.ru.service.model.Flat;
import se.ifmo.ru.service.model.Page;
import se.ifmo.ru.web.model.FlatAddOrUpdateRequestDto;

import java.util.List;

public interface FlatService {
    Page<Flat> getFlats(List<String> sortsList, List<String> filtersList, Integer page, Integer pageSize);

    Flat getFlat(long id);

    Flat updateFlat(long id, FlatAddOrUpdateRequestDto requestDto);

    Flat addFlat(FlatAddOrUpdateRequestDto requestDto);

    boolean deleteFlat(long id);

    Flat getWithMaxId();

    long countByNew(Boolean newValue);

    long sumHeight();
}
