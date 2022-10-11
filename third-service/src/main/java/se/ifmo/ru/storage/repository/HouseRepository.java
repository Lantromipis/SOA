package se.ifmo.ru.storage.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import se.ifmo.ru.storage.model.HouseEntity;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class HouseRepository implements PanacheRepositoryBase<HouseEntity, Long> {
    public List<HouseEntity> getHousesInCountry(long countryId){
        return list("country_id = ?1 and donated = ?2", countryId, false);
    }
}
