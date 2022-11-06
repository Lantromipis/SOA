package se.ifmo.ru.service.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

@RequiredArgsConstructor
public enum Transport {
    FEW("few"),
    NONE("none"),
    LITTLE("little"),
    NORMAL("normal"),
    ENOUGH("enough");

    @Getter
    private final String value;

    @Override
    public String toString() {
        return value;
    }

    public static Transport fromValue(String value){
        return Arrays.stream(Transport.values())
                .filter(e-> Objects.equals(e.getValue(), value))
                .findFirst()
                .orElse(NONE);
    }
}
