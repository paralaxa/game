package sk.stopangin.movement;

import lombok.Data;
import sk.stopangin.piece.Piece;

@Data
public class Movement<T> {
    private Piece<T> piece;
    private Coordinates<T> newPocition;
}
