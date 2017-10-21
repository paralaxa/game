package sk.stopangin.movement;

import lombok.Data;
import sk.stopangin.piece.Piece;

@Data
public class Movement<T> {
    private Piece piece;
    private Coordinates<T> newPocition;
    private MovementType<T> movementType;
}
