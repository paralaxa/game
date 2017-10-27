package sk.stopangin.board;

import sk.stopangin.field.Field;
import sk.stopangin.field.RegularField;
import sk.stopangin.movement.Coordinates;
import sk.stopangin.movement.TwoDimensionalCoordinates;
import sk.stopangin.movement.TwoDimensionalCoordinatesData;

import java.util.HashSet;
import java.util.Set;

public final class ChessboardGenerator {
    private ChessboardGenerator() {

    }

    public static Set<Field<TwoDimensionalCoordinatesData>> generateFieldsForChessboard() {
        Set<Field<TwoDimensionalCoordinatesData>> fields = new HashSet<>();
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                Field<TwoDimensionalCoordinatesData> field = new RegularField();
                Coordinates<TwoDimensionalCoordinatesData> twoDimensionalCoordinatesDataCoordinates =
                        new TwoDimensionalCoordinates(new TwoDimensionalCoordinatesData(i, j));
                field.setPosition(twoDimensionalCoordinatesDataCoordinates);
                fields.add(field);
            }
        }
        return fields;
    }
}
