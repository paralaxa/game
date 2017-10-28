package sk.stopangin.movement;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiagonalMovementTypeTest {
    DiagonalMovementType diagonalMovementType = new DiagonalMovementType();
    Coordinates<TwoDimensionalCoordinatesData> currentCoordinates = new TwoDimensionalCoordinates(new TwoDimensionalCoordinatesData(2,2));


    @Test
    public void isMatchForUpperLeft() throws Exception {
        Coordinates<TwoDimensionalCoordinatesData> newCoordinates = new TwoDimensionalCoordinates(new TwoDimensionalCoordinatesData(3,3));
        Assert.assertTrue(diagonalMovementType.isMatch(currentCoordinates, newCoordinates));
    }

    @Test
    public void isMatchForUpperRight() throws Exception {
        Coordinates<TwoDimensionalCoordinatesData> newCoordinates = new TwoDimensionalCoordinates(new TwoDimensionalCoordinatesData(1,3));
        Assert.assertTrue(diagonalMovementType.isMatch(currentCoordinates, newCoordinates));
    }

    @Test
    public void isMatchForLowerLeft() throws Exception {
        Coordinates<TwoDimensionalCoordinatesData> newCoordinates = new TwoDimensionalCoordinates(new TwoDimensionalCoordinatesData(3,1));
        Assert.assertTrue(diagonalMovementType.isMatch(currentCoordinates, newCoordinates));
    }

    @Test
    public void isMatchForLowerRight() throws Exception {
        Coordinates<TwoDimensionalCoordinatesData> newCoordinates = new TwoDimensionalCoordinates(new TwoDimensionalCoordinatesData(1,1));
        Assert.assertTrue(diagonalMovementType.isMatch(currentCoordinates, newCoordinates));
    }

    @Test
    public void isNotMatchForLowerRightMoreThanOnePiece() throws Exception {
        Coordinates<TwoDimensionalCoordinatesData> newCoordinates = new TwoDimensionalCoordinates(new TwoDimensionalCoordinatesData(0,0));
        Assert.assertFalse(diagonalMovementType.isMatch(currentCoordinates, newCoordinates));
    }

    @Test
    public void isNotMatchForNonDiagonalMovement() throws Exception {
        Coordinates<TwoDimensionalCoordinatesData> newCoordinates = new TwoDimensionalCoordinates(new TwoDimensionalCoordinatesData(3,2));
        Assert.assertFalse(diagonalMovementType.isMatch(currentCoordinates, newCoordinates));
    }

}