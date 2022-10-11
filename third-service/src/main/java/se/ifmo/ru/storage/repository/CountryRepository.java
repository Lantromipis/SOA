package se.ifmo.ru.storage.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import se.ifmo.ru.storage.model.CountryEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CountryRepository implements PanacheRepositoryBase<CountryEntity, Long> {
}
