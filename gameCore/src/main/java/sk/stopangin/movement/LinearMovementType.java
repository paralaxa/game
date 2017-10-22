package sk.stopangin.movement;


public class LinearMovementType implements MovementType<Integer> {
    @Override
    public boolean isMatch(Coordinates<Integer> currentCoordinates, Coordinates<Integer> newCoordinates) {
        return true;
    }
}
