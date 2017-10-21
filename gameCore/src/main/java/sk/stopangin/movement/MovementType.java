package sk.stopangin.movement;

import sk.stopangin.piece.Piece;

import java.util.Set;

//definuje sposob pohybu (napr sikmo, rovno, do L...)
public interface MovementType<T> {
    boolean isMatch(Coordinates<Integer> currentCoordinates,Coordinates<Integer> newCoordinates);
}
