package se.ifmo.ru.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class House {
    private long id;
    private String name;
    private long year;
    private long numberOfFloors;
    private long numberOfLifts;
    private boolean donated;
}
