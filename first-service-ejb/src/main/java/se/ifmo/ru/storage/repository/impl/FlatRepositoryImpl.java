package se.ifmo.ru.storage.repository.impl;

import org.apache.commons.collections4.CollectionUtils;
import se.ifmo.ru.ejb.model.Page;
import se.ifmo.ru.storage.model.Filter;
import se.ifmo.ru.storage.model.FlatEntity;
import se.ifmo.ru.storage.model.Sort;
import se.ifmo.ru.storage.repository.api.FlatRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class FlatRepositoryImpl implements FlatRepository {

    @PersistenceContext(unitName = "postgres", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @Override
    public FlatEntity findById(long id) {
        return entityManager.find(FlatEntity.class, id);
    }

    @Override
    @Transactional
    public FlatEntity save(FlatEntity entity) {
        if (entity == null) {
            return null;
        }

        entity.setCreationDate(new Date());

        return entityManager.merge(entity);
    }

    @Override
    @Transactional
    public boolean deleteById(long id) {
        return entityManager.createQuery("delete from FlatEntity f where f.id=:id")
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Page<FlatEntity> getSortedAndFilteredPage(List<Sort> sortList, List<Filter> filters, Integer page, Integer size) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FlatEntity> flatsQuery = criteriaBuilder.createQuery(FlatEntity.class);
        Root<FlatEntity> root = flatsQuery.from(FlatEntity.class);
        CriteriaQuery<FlatEntity> select = flatsQuery.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(filters)) {
            predicates = new ArrayList<>();

            for (Filter filter : filters) {
                switch (filter.getFilteringOperation()) {
                    case EQ:
                        predicates.add(criteriaBuilder.equal(
                                        root.get(filter.getFieldName()),
                                        getTypedFieldValue(filter.getFieldName(), filter.getFieldValue())
                                )
                        );
                        break;
                    case NEQ:
                        predicates.add(criteriaBuilder.notEqual(
                                        root.get(filter.getFieldName()),
                                        getTypedFieldValue(filter.getFieldName(), filter.getFieldValue())
                                )
                        );
                        break;
                    case GT:
                        predicates.add(criteriaBuilder.greaterThan(
                                        root.get(filter.getFieldName()),
                                        filter.getFieldValue()
                                )
                        );
                        break;
                    case LT:
                        predicates.add(criteriaBuilder.lessThan(
                                        root.get(filter.getFieldName()),
                                        filter.getFieldValue()
                                )
                        );
                        break;
                    case GTE:
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                                        root.get(filter.getFieldName()),
                                        filter.getFieldValue()
                                )
                        );
                        break;
                    case LTE:
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(
                                        root.get(filter.getFieldName()),
                                        filter.getFieldValue()
                                )
                        );
                        break;
                    case UNDEFINED:
                        break;
                }
            }

            select.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        }

        if (CollectionUtils.isNotEmpty(sortList)) {
            List<Order> orderList = new ArrayList<>();
            for (Sort sortItem : sortList) {
                if (sortItem.isDesc()) {
                    orderList.add(criteriaBuilder.desc(root.get(sortItem.getFieldName())));
                } else {
                    orderList.add(criteriaBuilder.asc(root.get(sortItem.getFieldName())));
                }
            }
            select.orderBy(orderList);
        }

        TypedQuery<FlatEntity> typedQuery = entityManager.createQuery(select);

        Page<FlatEntity> ret = new Page<>();

        if (page != null && size != null) {
            typedQuery.setFirstResult((page - 1) * size);
            typedQuery.setMaxResults(size);

            long countResult = 0;

            if (CollectionUtils.isEmpty(predicates)) {
                Query queryTotal = entityManager.createQuery("Select count(f.id) from FlatEntity f");
                countResult = (long) queryTotal.getSingleResult();
            } else {
                CriteriaBuilder qb = entityManager.getCriteriaBuilder();
                CriteriaQuery<Long> cq = qb.createQuery(Long.class);
                cq.select(qb.count(cq.from(FlatEntity.class)));
                cq.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
                countResult = entityManager.createQuery(cq).getSingleResult();
            }

            ret.setPage(page);
            ret.setPageSize(size);
            ret.setTotalPages((int) Math.ceil((countResult * 1.0) / size));
            ret.setTotalCount(countResult);
        }

        ret.setObjects(typedQuery.getResultList());

        return ret;
    }

    @Override
    public long countHeight() {
        return (long) entityManager.createQuery("select sum(f.height) from FlatEntity f").getSingleResult();
    }

    @Override
    public FlatEntity getWithMaxId() {
        return (FlatEntity) entityManager.createQuery("select f from FlatEntity f order by f.id desc ").setMaxResults(1).getSingleResult();
    }

    @Override
    public long countByNewField(Boolean newField) {
        return (long) entityManager.createQuery("select count(f) from FlatEntity f where f.newField=:newField").setParameter("newField", newField).getSingleResult();
    }

    private Object getTypedFieldValue(String fieldName, String fieldValue) {
        if (Objects.equals(fieldName, "newField")) {
            return Boolean.valueOf(fieldValue);
        } else {
            return fieldValue;
        }
    }
}
