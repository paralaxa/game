package sk.stopangin.board;

import sk.stopangin.field.Field;
import sk.stopangin.movement.Movement;
import sk.stopangin.movement.TwoDimensionalCoordinatesData;

import java.util.Set;


public abstract class RectangularBoard extends Board<TwoDimensionalCoordinatesData> {
    @Override
    protected boolean isMoveOutOfBoundaries(Movement<TwoDimensionalCoordinatesData> movement) {
        return false;
    }

    public RectangularBoard(Set<Field<TwoDimensionalCoordinatesData>> fields) {
        super(fields);
    }
}
