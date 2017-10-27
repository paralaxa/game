package sk.stopangin.movement;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Coordinates<T extends Serializable> {
    private T data;
}
