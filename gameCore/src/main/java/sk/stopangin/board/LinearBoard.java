package sk.stopangin.board;

import sk.stopangin.field.Field;

import java.util.Set;

public abstract class LinearBoard extends Board<Integer> {
    public LinearBoard(Set<Field<Integer>> fields) {
        super(fields);
    }


}
