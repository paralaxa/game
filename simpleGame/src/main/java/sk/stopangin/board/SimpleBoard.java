package sk.stopangin.board;

import sk.stopangin.field.Field;
import sk.stopangin.movement.Movement;

import java.util.Set;

public class SimpleBoard extends LinearBoard {

    @Override
    public void initialize(Set<Field> fields) {

    }

    @Override
    protected boolean isMovementCollision(Movement movement) {
        return false;
    }

    @Override
    public boolean isMoveOutOfBoundaries(Movement movement) {
        return false;
    }
}
