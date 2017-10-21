package sk.stopangin.board;

import sk.stopangin.field.Field;
import sk.stopangin.movement.Movement;

import java.util.Set;

public abstract class LinearBoard extends Board {
    public LinearBoard(Set<Field> fields) {
        super(fields);
    }


    @Override
    protected boolean isMoveOutOfBoundaries(Movement movement) {
        return false;
    }

    @Override
    protected boolean isMovementCollision(Movement movement) {
        return false;
    }
}
