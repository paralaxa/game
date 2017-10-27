package sk.stopangin.movement;

import java.io.Serializable;

//definuje sposob pohybu (napr sikmo, rovno, do L...)
public interface MovementType<T extends Serializable> {
    boolean isMatch(Coordinates<T> currentCoordinates,Coordinates<T> newCoordinates);
}
