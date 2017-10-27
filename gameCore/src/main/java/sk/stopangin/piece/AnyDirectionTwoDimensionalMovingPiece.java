package sk.stopangin.piece;

import sk.stopangin.movement.MovementType;
import sk.stopangin.movement.TwoDimensionalCoordinatesData;

public class AnyDirectionTwoDimensionalMovingPiece extends Piece<TwoDimensionalCoordinatesData> {

    public AnyDirectionTwoDimensionalMovingPiece(String name, MovementType<TwoDimensionalCoordinatesData>... varMovementTypes) {
        super(name, varMovementTypes);
    }
}
