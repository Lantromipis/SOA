package se.ifmo.ru.service.model;

import lombok.Data;

@Data
public class Coordinates {
    private float x; //Значение поля должно быть больше -292
    private Integer y; //Значение поля должно быть больше -747, Поле не может быть null
}
