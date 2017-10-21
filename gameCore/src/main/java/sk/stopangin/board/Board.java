package sk.stopangin.board;

import lombok.Data;
import sk.stopangin.field.Field;
import sk.stopangin.movement.Movement;

import java.util.Set;

@Data
public abstract class Board {
    private Set<Field> fields;

    public abstract boolean isMoveWithinBoundaries(Movement movement);
}
