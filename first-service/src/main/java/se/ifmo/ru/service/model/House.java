package se.ifmo.ru.service.model;

import lombok.Data;

@Data
public class House {
    private String name; //Поле не может быть null
    private Integer year; //Значение поля должно быть больше 0
    private long numberOfFloors; //Значение поля должно быть больше 0
    private Long numberOfLifts; //Значение поля должно быть больше 0
}
