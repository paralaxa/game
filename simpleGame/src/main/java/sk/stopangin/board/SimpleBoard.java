package sk.stopangin.board;

import sk.stopangin.field.Field;
import sk.stopangin.movement.Coordinates;
import sk.stopangin.movement.Movement;

import java.util.Set;

public class SimpleBoard extends LinearBoard {

    public SimpleBoard(Set<Field<Integer>> fields) {
        super(fields);
    }

    @Override
    public boolean isMoveOutOfBoundaries(Movement<Integer> movement) {
        return movement.getNewPosition().getData() > getFields().size();
    }

    @Override
    public Coordinates<Integer> getCoordinatesForPieceId(Long pieceId) {
        for (Field<Integer> field : getFields()) {
            if (field.getPiece() != null && field.getPiece().getId().equals(pieceId)) {
                return field.getPosition();
            }
        }
        return new Coordinates<>(0);
    }
}
