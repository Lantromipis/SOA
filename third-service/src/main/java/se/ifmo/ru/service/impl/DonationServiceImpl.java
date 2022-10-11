package se.ifmo.ru.service.impl;

import se.ifmo.ru.service.api.DonationService;
import se.ifmo.ru.storage.model.HouseEntity;
import se.ifmo.ru.storage.model.SponsorEntity;
import se.ifmo.ru.storage.repository.HouseRepository;
import se.ifmo.ru.storage.repository.SponsorRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class DonationServiceImpl implements DonationService {
    @Inject
    SponsorRepository sponsorRepository;

    @Inject
    HouseRepository houseRepository;

    @Override
    @Transactional
    public void donate(long houseId, long sponsorId, double money) {
        SponsorEntity sponsor = sponsorRepository.findById(sponsorId);
        if (sponsor == null) {
            throw new IllegalArgumentException("Sponsor not found");
        }
        HouseEntity houseEntity = houseRepository.findById(houseId);

        if (houseEntity == null || houseEntity.isDonated()) {
            throw new IllegalArgumentException("House not found");
        }

        houseEntity.setDonated(true);
        houseEntity.setSponsor(sponsor);
    }
}
