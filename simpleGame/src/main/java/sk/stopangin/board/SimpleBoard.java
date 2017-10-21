package sk.stopangin.board;

import sk.stopangin.movement.Movement;

public class SimpleBoard extends LinearBoard {

    @Override
    protected boolean isMovementCollision(Movement movement) {
        return false;
    }

    @Override
    public boolean isMoveOutOfBoundaries(Movement movement) {
        return false;
    }
}
