package sk.stopangin.movement;

public class DiagonalMovementType implements MovementType<TwoDimensionalCoordinatesData> {
    private int[][] movementMatrix = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    private MatrixBasedMovement matrixBasedMovement = new MatrixBasedMovement(movementMatrix);

    @Override
    public boolean isMatch(Coordinates<TwoDimensionalCoordinatesData> currentCoordinates, Coordinates<TwoDimensionalCoordinatesData> newCoordinates) {
        return matrixBasedMovement.isMatch(currentCoordinates, newCoordinates);
    }
}
