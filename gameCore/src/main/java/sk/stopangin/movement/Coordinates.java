package sk.stopangin.movement;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Coordinates<T> {
    private T data;
}
