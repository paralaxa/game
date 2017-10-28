package sk.stopangin.board;

import sk.stopangin.field.Field;
import sk.stopangin.movement.Coordinates;
import sk.stopangin.movement.TwoDimensionalCoordinatesData;

import java.util.Set;


public abstract class RectangularBoard extends Board<TwoDimensionalCoordinatesData> {

    public RectangularBoard(Set<Field<TwoDimensionalCoordinatesData>> fields) {
        super(fields);
    }

    @Override
    public Coordinates<TwoDimensionalCoordinatesData> getCoordinatesForPieceId(Long pieceId) {
        for (Field<TwoDimensionalCoordinatesData> field : getFields()) {
            if (field.getPiece() != null && field.getPiece().getId().equals(pieceId)) {
                return field.getPosition();
            }
        }
        return new Coordinates<>(new TwoDimensionalCoordinatesData(1, 1));
    }
}
