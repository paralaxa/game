package sk.stopangin.board;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sk.stopangin.field.Field;
import sk.stopangin.movement.Movement;
import sk.stopangin.movement.MovementStatus;
import sk.stopangin.movement.TwoDimensionalCoordinates;
import sk.stopangin.movement.TwoDimensionalCoordinatesData;
import sk.stopangin.piece.AnyDirectionTwoDimensionalMovingPiece;
import sk.stopangin.piece.Piece;

public class BoardTest {

    private RectangularBoard rectangularBoard;

    @Before
    public void setUp() throws Exception {
        rectangularBoard = new RectangularBoard(RectangularBoardGenerator.generateFields(9, new TwoDimensionalCoordinatesData(1, 2))) {
        };
        Field<TwoDimensionalCoordinatesData> field1_1 = getFieldForCoordinatesData(new TwoDimensionalCoordinatesData(1, 1));
        Field<TwoDimensionalCoordinatesData> field2_1 = getFieldForCoordinatesData(new TwoDimensionalCoordinatesData(2, 1));
        Field<TwoDimensionalCoordinatesData> field3_1 = getFieldForCoordinatesData(new TwoDimensionalCoordinatesData(3, 1));
        Piece<TwoDimensionalCoordinatesData> piece1_1 = new AnyDirectionTwoDimensionalMovingPiece(1l, "piece1_1");
        Piece<TwoDimensionalCoordinatesData> piece2_1 = new AnyDirectionTwoDimensionalMovingPiece(2l, "piece2_1");
        Piece<TwoDimensionalCoordinatesData> piece3_1 = new AnyDirectionTwoDimensionalMovingPiece(3l, "piece3_1");
        field1_1.setPiece(piece1_1);
        field2_1.setPiece(piece2_1);
        field3_1.setPiece(piece3_1);
    }

    @Test
    public void updateBasedOnValidMovement() throws Exception {
        Movement<TwoDimensionalCoordinatesData> movement = new Movement<>();
        movement.setPieceId(1l);
        movement.setNewPosition(new TwoDimensionalCoordinates(new TwoDimensionalCoordinatesData(2, 2)));
        MovementStatus movementStatus = rectangularBoard.updateBasedOnMovement(movement);
        Assert.assertEquals(MovementStatus.DONE, movementStatus);
        Assert.assertEquals(new TwoDimensionalCoordinates(new TwoDimensionalCoordinatesData(2, 2)), rectangularBoard.getCoordinatesForPieceId(1l));
    }

    @Test
    public void updateBasedOnValidMovementToActionFieldWithActionPossible() throws Exception {
        Movement<TwoDimensionalCoordinatesData> movement = new Movement<>();
        movement.setPieceId(1l);
        movement.setNewPosition(new TwoDimensionalCoordinates(new TwoDimensionalCoordinatesData(1, 2)));
        MovementStatus movementStatus = rectangularBoard.updateBasedOnMovement(movement);
        Assert.assertEquals(MovementStatus.ACTION_POSSIBLE, movementStatus);
        Assert.assertEquals(new TwoDimensionalCoordinates(new TwoDimensionalCoordinatesData(1, 1)), rectangularBoard.getCoordinatesForPieceId(1l));
    }

    @Test
    public void updateBasedOnInvalidMovementOutOfBoard() throws Exception {
        Movement<TwoDimensionalCoordinatesData> movement = new Movement<>();
        movement.setPieceId(1l);
        movement.setNewPosition(new TwoDimensionalCoordinates(new TwoDimensionalCoordinatesData(2, 9)));
        MovementStatus movementStatus = rectangularBoard.updateBasedOnMovement(movement);
        Assert.assertEquals(MovementStatus.NON_EXISTING_FIELD, movementStatus);
        Assert.assertEquals(new TwoDimensionalCoordinates(new TwoDimensionalCoordinatesData(1, 1)), rectangularBoard.getCoordinatesForPieceId(1l));
    }

    @Test
    public void updateBasedOnInvalidMovementCollision() throws Exception {
        Movement<TwoDimensionalCoordinatesData> movement = new Movement<>();
        movement.setPieceId(1l);
        movement.setNewPosition(new TwoDimensionalCoordinates(new TwoDimensionalCoordinatesData(2, 1)));
        MovementStatus movementStatus = rectangularBoard.updateBasedOnMovement(movement);
        Assert.assertEquals(MovementStatus.COLLISION, movementStatus);
        Assert.assertEquals(new TwoDimensionalCoordinates(new TwoDimensionalCoordinatesData(1, 1)), rectangularBoard.getCoordinatesForPieceId(1l));
    }

    @Test
    public void updateBasedOnInvalidMovementType() throws Exception {
        Movement<TwoDimensionalCoordinatesData> movement = new Movement<>();
        movement.setPieceId(1l);
        movement.setNewPosition(new TwoDimensionalCoordinates(new TwoDimensionalCoordinatesData(2, 3)));
        MovementStatus movementStatus = rectangularBoard.updateBasedOnMovement(movement);
        Assert.assertEquals(MovementStatus.INVALID_POSITION, movementStatus);
        Assert.assertEquals(new TwoDimensionalCoordinates(new TwoDimensionalCoordinatesData(1, 1)), rectangularBoard.getCoordinatesForPieceId(1l));
    }

    private Field<TwoDimensionalCoordinatesData> getFieldForCoordinatesData(TwoDimensionalCoordinatesData twoDimensionalCoordinatesData) {
        for (Field<TwoDimensionalCoordinatesData> twoDimensionalCoordinatesDataField : rectangularBoard.getFields()) {
            if (twoDimensionalCoordinatesDataField.getPosition().equals(new TwoDimensionalCoordinates(twoDimensionalCoordinatesData))) {
                return twoDimensionalCoordinatesDataField;
            }
        }
        return null;
    }

}