package se.ifmo.ru.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import se.ifmo.ru.mapper.FlatMapper;
import se.ifmo.ru.service.api.FlatService;
import se.ifmo.ru.service.model.Flat;
import se.ifmo.ru.service.model.Transport;
import se.ifmo.ru.storage.model.Page;
import se.ifmo.ru.storage.repository.impl.FlatRepositoryImpl;
import se.ifmo.ru.storage.model.Filter;
import se.ifmo.ru.storage.model.FilteringOperation;
import se.ifmo.ru.storage.model.FlatEntity;
import se.ifmo.ru.storage.model.Sort;
import se.ifmo.ru.web.model.FlatAddOrUpdateRequestDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApplicationScoped
public class FlatServiceImpl implements FlatService {
    @Inject
    FlatRepositoryImpl flatDao;

    @Inject
    FlatMapper flatMapper;

    @Override
    public Page<Flat> getFlats(List<String> sortsList, List<String> filtersList, Integer page, Integer pageSize) {
        if (page != null || pageSize != null) {
            if (page == null) {
                page = 0;
            }
            if (pageSize == null) {
                pageSize = 20;
            }
        }

        Pattern nestedFieldNamePattern = Pattern.compile("(.*)\\.(.*)");
        Pattern lhsPattern = Pattern.compile("(.*)\\[(.*)\\]=(.*)");

        List<Sort> sorts = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(sortsList)) {
            boolean containsOppositeSorts = sortsList.stream().anyMatch(e1 ->
                    sortsList.stream().anyMatch(e2 -> Objects.equals(e1, "-" + e2))
            );

            if (containsOppositeSorts) {
                throw new IllegalArgumentException("Request contains opposite sort parameters");
            }

            for (String sort : sortsList) {
                boolean desc = sort.startsWith("-");
                String sortFieldName = desc ? sort.split("-")[1] : sort;

                Matcher matcher = nestedFieldNamePattern.matcher(sortFieldName);
                if (matcher.find()) {
                    String nestedField = matcher.group(2).substring(0, 1).toUpperCase() + matcher.group(2).substring(1);
                    sortFieldName = matcher.group(1) + nestedField;
                }

                if (Objects.equals(sortFieldName, "new")) {
                    sortFieldName = "newField";
                }

                sorts.add(Sort
                        .builder()
                        .desc(desc)
                        .fieldName(sortFieldName)
                        .build()
                );
            }
        }

        List<Filter> filters = new ArrayList<>();

        for (String filter : filtersList) {
            Matcher matcher = lhsPattern.matcher(filter);
            String fieldName = null, fieldValue = null;
            FilteringOperation filteringOperation = null;

            if (matcher.find()) {
                fieldName = matcher.group(1);

                Matcher nestedFieldMatcher = nestedFieldNamePattern.matcher(fieldName);
                if (nestedFieldMatcher.find()) {
                    String nestedField = nestedFieldMatcher.group(2).substring(0, 1).toUpperCase() + nestedFieldMatcher.group(2).substring(1);
                    fieldName = nestedFieldMatcher.group(1) + nestedField;
                }

                filteringOperation = FilteringOperation.fromValue(matcher.group(2));
                if (Objects.equals(fieldName, "new")) {
                    if (!Objects.equals(filteringOperation, FilteringOperation.EQ) && !Objects.equals(filteringOperation, FilteringOperation.NEQ)) {
                        throw new IllegalArgumentException("Only [eq] and [neq] operations are allowed for \"new\" field");
                    }
                    fieldName = "newField";
                }
                fieldValue = matcher.group(3);
            }

            if (StringUtils.isEmpty(fieldName)) {
                throw new IllegalArgumentException("Filter field name is empty");
            }
            if (StringUtils.isEmpty(fieldValue)) {
                throw new IllegalArgumentException("Filter field value is empty");
            }
            if (Objects.equals(filteringOperation, FilteringOperation.UNDEFINED)) {
                throw new IllegalArgumentException("No or unknown filtering operation. Possible values are: eq,neq,gt,lt,gte,lte.");
            }

            filters.add(Filter.builder()
                    .fieldName(fieldName)
                    .fieldValue(fieldValue)
                    .filteringOperation(filteringOperation)
                    .build()
            );
        }
        Page<FlatEntity> entitiesPage;

        try {
            entitiesPage = flatDao.getSortedAndFilteredPage(sorts, filters, page, pageSize);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error while getting page. Check query params format. " + e.getMessage(), e);
        }

        Page<Flat> ret = new Page<>();
        ret.setObjects(flatMapper.fromEntityList(entitiesPage.getObjects()));
        ret.setPage(entitiesPage.getPage());
        ret.setPageSize(entitiesPage.getPageSize());
        ret.setTotalPages(entitiesPage.getTotalPages());
        ret.setTotalCount(entitiesPage.getTotalCount());

        return ret;
    }

    @Override
    public Flat getFlat(long id) {
        return flatMapper.fromEntity(flatDao.findById(id));
    }

    @Override
    public Flat updateFlat(long id, FlatAddOrUpdateRequestDto requestDto) {
        FlatEntity flatEntity = flatDao.findById(id);

        if (flatEntity == null) {
            return null;
        }

        flatEntity.setName(requestDto.getName());
        flatEntity.setArea(requestDto.getArea());
        flatEntity.setPrice(requestDto.getPrice());
        flatEntity.setHeight(requestDto.getHeight());
        flatEntity.setNumberOfRooms(requestDto.getNumberOfRooms());
        flatEntity.setNewField(requestDto.getNewField());
        flatEntity.setTransport(Transport.fromValue(requestDto.getTransport()));

        flatEntity.setCoordinatesX(requestDto.getCoordinates().getX());
        flatEntity.setCoordinatesY(requestDto.getCoordinates().getY());

        flatEntity.setHouseName(requestDto.getHouse().getName());
        flatEntity.setHouseYear(requestDto.getHouse().getYear());
        flatEntity.setHouseNumberOfLifts(requestDto.getHouse().getNumberOfLifts());
        flatEntity.setHouseNumberOfFloors(requestDto.getHouse().getNumberOfFloors());

        flatEntity = flatDao.save(flatEntity);

        return flatMapper.fromEntity(flatEntity);
    }

    @Override
    public Flat addFlat(FlatAddOrUpdateRequestDto requestDto) {
        FlatEntity flatEntity = FlatEntity
                .builder()
                .name(requestDto.getName())
                .area(requestDto.getArea())
                .coordinatesX(requestDto.getCoordinates().getX())
                .coordinatesY(requestDto.getCoordinates().getY())
                .price(requestDto.getPrice())
                .height(requestDto.getHeight())
                .houseName(requestDto.getHouse().getName())
                .houseYear(requestDto.getHouse().getYear())
                .houseNumberOfFloors(requestDto.getHouse().getNumberOfFloors())
                .houseNumberOfLifts(requestDto.getHouse().getNumberOfLifts())
                .numberOfRooms(requestDto.getNumberOfRooms())
                .newField(requestDto.getNewField())
                .transport(Transport.fromValue(requestDto.getTransport()))
                .build();

        return flatMapper.fromEntity(flatDao.save(flatEntity));
    }

    @Override
    public boolean deleteFlat(long id) {
        return flatDao.deleteById(id);
    }

    @Override
    public Flat getWithMaxId() {
        return flatMapper.fromEntity(flatDao.getWithMaxId());
    }

    @Override
    public long countByNew(Boolean newValue) {
        return flatDao.countByNewField(newValue);
    }

    @Override
    public long sumHeight() {
        return flatDao.countHeight();
    }
}
