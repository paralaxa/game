package sk.stopangin.movement;


import sk.stopangin.piece.Piece;

import java.util.HashSet;
import java.util.Set;

public class LinearMovementType implements MovementType<Integer> {
    @Override
    public Set<Coordinates<Integer>> getPossibleCoordinatesForPiece(Piece piece) {
        return new HashSet<>();
    }
}
