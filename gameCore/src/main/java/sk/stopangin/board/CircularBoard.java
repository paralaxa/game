package sk.stopangin.board;

import sk.stopangin.field.Field;
import sk.stopangin.movement.Movement;

import java.util.Set;

public abstract class CircularBoard extends Board {
    public CircularBoard(Set<Field> fields) {
        super(fields);
    }

}
