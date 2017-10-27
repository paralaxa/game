package sk.stopangin.movement;

public class DirectMovementType implements MovementType<TwoDimensionalCoordinatesData> {
    @Override
    public boolean isMatch(Coordinates<TwoDimensionalCoordinatesData> currentCoordinates, Coordinates<TwoDimensionalCoordinatesData> newCoordinates) {
        TwoDimensionalCoordinatesData currentCoordinatesData = currentCoordinates.getData();
        TwoDimensionalCoordinatesData newCoordinatesData = newCoordinates.getData();
        if (currentCoordinatesData.getX() == newCoordinatesData.getX()) {
            return currentCoordinatesData.getY() != newCoordinatesData.getY();
        }
        return currentCoordinatesData.getX() != newCoordinatesData.getX();
    }
}
