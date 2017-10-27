package sk.stopangin.board;

import sk.stopangin.field.Field;
import sk.stopangin.field.RegularField;
import sk.stopangin.movement.Coordinates;
import sk.stopangin.movement.TwoDimensionalCoordinates;
import sk.stopangin.movement.TwoDimensionalCoordinatesData;

import java.util.HashSet;
import java.util.Set;

public class RandomSimpleGameBoardGenerator {
    private static final int FIELDS_COUNT = 64;

    private RandomSimpleGameBoardGenerator() {
    }

    public static Board generate() {

        Set<Field<TwoDimensionalCoordinatesData>> fields = new HashSet<>();
        for (int i = 0; i < FIELDS_COUNT; i++) {
            Field<TwoDimensionalCoordinatesData> field = new RegularField();
            Coordinates<TwoDimensionalCoordinatesData> twoDimensionalCoordinatesDataCoordinates =
                    new TwoDimensionalCoordinates(new TwoDimensionalCoordinatesData("A", i));

            field.setPosition(twoDimensionalCoordinatesDataCoordinates);
            fields.add(field);
        }
        return new SimpleBoard(fields);
    }
}
