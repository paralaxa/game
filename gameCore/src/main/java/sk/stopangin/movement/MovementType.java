package sk.stopangin.movement;

import sk.stopangin.piece.Piece;

import java.util.Set;

//definuje sposob pohybu (napr sikmo, rovno, do L...)
public interface MovementType<T> {
    Set<Coordinates<T>> getPossibleCoordinatesForPiece(Piece piece);
}
