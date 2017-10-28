package sk.stopangin.movement;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DirectMovementTypeTest {
    DirectMovementType directMovementType= new DirectMovementType();
    Coordinates<TwoDimensionalCoordinatesData> currentCoordinates = new TwoDimensionalCoordinates(new TwoDimensionalCoordinatesData(2,2));

    @Test
    public void isMatchForLeft() throws Exception {
        Coordinates<TwoDimensionalCoordinatesData> newCoordinates = new TwoDimensionalCoordinates(new TwoDimensionalCoordinatesData(3,2));
        Assert.assertTrue(directMovementType.isMatch(currentCoordinates, newCoordinates));
    }

    @Test
    public void isMatchForRight() throws Exception {
        Coordinates<TwoDimensionalCoordinatesData> newCoordinates = new TwoDimensionalCoordinates(new TwoDimensionalCoordinatesData(1,2));
        Assert.assertTrue(directMovementType.isMatch(currentCoordinates, newCoordinates));
    }

    @Test
    public void isMatchForUp() throws Exception {
        Coordinates<TwoDimensionalCoordinatesData> newCoordinates = new TwoDimensionalCoordinates(new TwoDimensionalCoordinatesData(2,3));
        Assert.assertTrue(directMovementType.isMatch(currentCoordinates, newCoordinates));
    }

    @Test
    public void isMatchForDown() throws Exception {
        Coordinates<TwoDimensionalCoordinatesData> newCoordinates = new TwoDimensionalCoordinates(new TwoDimensionalCoordinatesData(2,1));
        Assert.assertTrue(directMovementType.isMatch(currentCoordinates, newCoordinates));
    }

    @Test
    public void isNotMatchForUpMoreThanOnePiece() throws Exception {
        Coordinates<TwoDimensionalCoordinatesData> newCoordinates = new TwoDimensionalCoordinates(new TwoDimensionalCoordinatesData(2,4));
        Assert.assertFalse(directMovementType.isMatch(currentCoordinates, newCoordinates));
    }

    @Test
    public void isNotMatchForNonDirectMovement() throws Exception {
        Coordinates<TwoDimensionalCoordinatesData> newCoordinates = new TwoDimensionalCoordinates(new TwoDimensionalCoordinatesData(3,3));
        Assert.assertFalse(directMovementType.isMatch(currentCoordinates, newCoordinates));
    }
}
