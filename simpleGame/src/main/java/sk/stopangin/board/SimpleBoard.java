package sk.stopangin.board;

import sk.stopangin.field.Field;
import sk.stopangin.movement.Movement;

import java.util.Set;

public class SimpleBoard extends LinearBoard {

    public SimpleBoard(Set<Field> fields) {
        super(fields);
    }


    @Override
    protected boolean isMovementCollision(Movement movement) {
        return true;
    }

    @Override
    public boolean isMoveOutOfBoundaries(Movement movement) {
        return false;
    }
}
