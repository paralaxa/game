package sk.stopangin.board;

import sk.stopangin.field.Field;
import sk.stopangin.field.RegularField;

import java.util.HashSet;
import java.util.Set;

public class RandomSimpleGameBoardGenerator {
    private static final int FIELDS_COUNT = 70;

    public static Board generate() {

        Set<Field> fields = new HashSet<>();
        for (int i = 0; i < FIELDS_COUNT; i++) {
            Field field = new RegularField();
            fields.add(field);
        }
        return SimpleBoard.builder().fields(fields).build();
    }
}
