package se.ifmo.ru.model.bus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SponsorBusData {
    private String sponsorId;
    private String houseId;
    private Double amount;
}
