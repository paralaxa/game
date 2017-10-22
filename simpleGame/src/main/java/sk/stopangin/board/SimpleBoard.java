package sk.stopangin.board;

import sk.stopangin.field.Field;
import sk.stopangin.movement.Movement;

import java.util.Set;

public class SimpleBoard extends LinearBoard {

    public SimpleBoard(Set<Field<Integer>> fields) {
        super(fields);
    }


    @Override
    protected boolean isMovementCollision(Movement<Integer> movement) {
        return false;
    }

    @Override
    public boolean isMoveOutOfBoundaries(Movement<Integer> movement) {
        return movement.getNewPocition().getData()>getFields().size();
    }
}
