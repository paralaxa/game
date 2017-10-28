package sk.stopangin.piece;

import sk.stopangin.movement.LinearMovementType;

public class LinearMovingPiece extends Piece<Integer> {


    public LinearMovingPiece(Long id, String name) {
        super(id, name, new LinearMovementType());
    }

}
