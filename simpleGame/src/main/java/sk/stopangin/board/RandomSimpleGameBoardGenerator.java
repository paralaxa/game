package sk.stopangin.board;

import sk.stopangin.field.Field;
import sk.stopangin.field.RegularField;
import sk.stopangin.movement.Coordinates;

import java.util.HashSet;
import java.util.Set;

public class RandomSimpleGameBoardGenerator {
    private static final int FIELDS_COUNT = 70;

    private RandomSimpleGameBoardGenerator() {
    }

    public static Board generate() {

        Set<Field<Integer>> fields = new HashSet<>();
        for (int i = 0; i < FIELDS_COUNT; i++) {
            Field<Integer> field = new RegularField();
            field.setPosition(new Coordinates(i));
            fields.add(field);
        }
        return new SimpleBoard(fields);
    }
}
