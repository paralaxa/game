package sk.stopangin.board;

import lombok.Data;
import sk.stopangin.field.Field;
import sk.stopangin.movement.Coordinates;
import sk.stopangin.movement.Movement;
import sk.stopangin.movement.MovementStatus;
import sk.stopangin.piece.Piece;

import java.io.Serializable;
import java.util.Set;

@Data
public abstract class Board<T extends Serializable> {
    private Set<Field<T>> fields;

    public Board(Set<Field<T>> fields) {
        this.fields = fields;
    }

    public MovementStatus updateBasedOnMovement(Movement<T> movement) {
        if (isMoveOutOfBoundaries(movement)) {
            return MovementStatus.OUT_OF_BOUNDARIES;
        }
        if (isMovementCollision(movement)) {
            return MovementStatus.COLLISION;
        }
        return updateBoard(movement);
    }

    private MovementStatus updateBoard(Movement<T> movement) {
        for (Field<T> field : fields) {
            Long currentMovementPieceId = movement.getPieceId();
            Piece<T> pieceFromField = getPieceFromField(field, currentMovementPieceId);
            if (pieceFromField != null) {
                if (pieceFromField.isValidMove(field.getPosition(), movement.getNewPosition())) {
                    removePieceFromPield(field);
                } else {
                    return MovementStatus.INVALID_POSITION;
                }
            }
            putPieceOnNewField(movement, field, pieceFromField);
        }
        return MovementStatus.DONE;
    }

    public Field<T> getFieldForCoordinates(Coordinates<T> coordinates) {
        return fields.stream()
                .filter(tField -> tField.getPosition().equals(coordinates))
                .findFirst()
                .get();
    }

    protected Piece<T> getPieceForCoordinates(Coordinates<T> coordinates) {
        for (Field<T> field : fields) {
            if (field.getPosition().equals(coordinates)) {
                return field.getPiece();
            }
        }
        return null;
    }


    private Piece<T> getPieceFromField(Field<T> field, Long currentMovementPieceId) {
        if (isCurrentMovementsPieceOnField(currentMovementPieceId, field)) {
            return field.getPiece();
        }
        return null;
    }

    private boolean isCurrentMovementsPieceOnField(Long currentMovementPieceId, Field<T> field) {
        return field.getPiece() != null && field.getPiece().getId().equals(currentMovementPieceId);
    }

    private void putPieceOnNewField(Movement<T> movement, Field<T> field, Piece<T> currentMovementPiece) {
        if (field.getPosition().equals(movement.getNewPosition())) {
            field.setPiece(currentMovementPiece);
        }
    }

    private void removePieceFromPield(Field<T> field) {
        field.setPiece(null);
    }


    protected abstract boolean isMoveOutOfBoundaries(Movement<T> movement);

    protected boolean isMovementCollision(Movement<T> movement) {
        return getPieceForCoordinates(movement.getNewPosition()) != null;
    }

    public abstract Coordinates<T> getCoordinatesForPieceId(Long pieceId);
}
