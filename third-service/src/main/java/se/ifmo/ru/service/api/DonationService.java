package se.ifmo.ru.service.api;

public interface DonationService {
    void donate(long houseId, long sponsorId, double money);
}
