package sk.stopangin.board;

import sk.stopangin.movement.Movement;

public class SimpleBoard extends LinearBoard {

    @Override
    public boolean isMoveWithinBoundaries(Movement movement) {
        return false;
    }
}
