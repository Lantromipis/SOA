package se.ifmo.ru.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Flat {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double area; //Поле может быть null, Значение поля должно быть больше 0
    private long numberOfRooms; //Значение поля должно быть больше 0
    private Integer height; //Значение поля должно быть больше 0
    private Boolean newField; //Поле может быть null
    private Transport transport; //Поле не может быть null
    private House house; //Поле не может быть null
    private Double price; //Значение поля должно быть больше 0
}
