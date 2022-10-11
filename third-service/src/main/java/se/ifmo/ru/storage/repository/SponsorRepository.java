package se.ifmo.ru.storage.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import se.ifmo.ru.storage.model.SponsorEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SponsorRepository implements PanacheRepositoryBase<SponsorEntity, Long> {
}
