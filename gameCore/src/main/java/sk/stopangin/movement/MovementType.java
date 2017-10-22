package sk.stopangin.movement;

//definuje sposob pohybu (napr sikmo, rovno, do L...)
public interface MovementType<T> {
    boolean isMatch(Coordinates<Integer> currentCoordinates,Coordinates<T> newCoordinates);
}
