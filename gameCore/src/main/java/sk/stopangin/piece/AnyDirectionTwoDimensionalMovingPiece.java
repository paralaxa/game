package sk.stopangin.piece;

import sk.stopangin.movement.DirectMovementType;
import sk.stopangin.movement.DiagonalMovementType;
import sk.stopangin.movement.TwoDimensionalCoordinatesData;

public class AnyDirectionTwoDimensionalMovingPiece extends Piece<TwoDimensionalCoordinatesData> {

    public AnyDirectionTwoDimensionalMovingPiece(Long id, String name) {
        super(id, name, new DirectMovementType(), new DiagonalMovementType());
    }
}
