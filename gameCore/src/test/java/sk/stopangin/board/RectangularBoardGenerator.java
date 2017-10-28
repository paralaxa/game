package sk.stopangin.board;

import sk.stopangin.field.*;
import sk.stopangin.game.Game;
import sk.stopangin.movement.Coordinates;
import sk.stopangin.movement.TwoDimensionalCoordinates;
import sk.stopangin.movement.TwoDimensionalCoordinatesData;

import java.util.HashSet;
import java.util.Set;

public final class RectangularBoardGenerator {
    private RectangularBoardGenerator() {

    }

    public static Set<Field<TwoDimensionalCoordinatesData>> generateFields(int max, TwoDimensionalCoordinatesData... actionFieldsCoordinatesData) {
        Set<Field<TwoDimensionalCoordinatesData>> fields = new HashSet<>();
        for (int i = 1; i < max; i++) {
            for (int j = 1; j < max; j++) {
                TwoDimensionalCoordinatesData currentFieldCoordinatesData = new TwoDimensionalCoordinatesData(i, j);
                Field<TwoDimensionalCoordinatesData> field = getFieldType(currentFieldCoordinatesData, actionFieldsCoordinatesData);
                Coordinates<TwoDimensionalCoordinatesData> twoDimensionalCoordinatesDataCoordinates =
                        new TwoDimensionalCoordinates(currentFieldCoordinatesData);
                field.setPosition(twoDimensionalCoordinatesDataCoordinates);
                fields.add(field);
            }
        }
        return fields;
    }

    private static Field<TwoDimensionalCoordinatesData> getFieldType(TwoDimensionalCoordinatesData currentFieldCoordinatesData, TwoDimensionalCoordinatesData... actionFieldsCoordinatesData) {
        for (TwoDimensionalCoordinatesData actionFieldsCoordinatesDatum : actionFieldsCoordinatesData) {
            if (currentFieldCoordinatesData.equals(actionFieldsCoordinatesDatum)) {
                ActionField<Integer,TwoDimensionalCoordinatesData,Void> actionField = new ActionField<>();
                Action<Integer, TwoDimensionalCoordinatesData, Void> action = getMockAction();
                ActionData actionData = new ActionData();
                actionData.setInfo("kolko");
                actionData.setData("vela");
                action.setActionData(actionData);
                actionField.setAction(action);
                return actionField;
            }
        }
        return new RegularField<>();
    }

    private static Action<Integer, TwoDimensionalCoordinatesData, Void> getMockAction() {
        return new Action<Integer, TwoDimensionalCoordinatesData, Void>() {
            @Override
            public Integer perform(Game<TwoDimensionalCoordinatesData, Void> game) {
                return null;
            }

            @Override
            public Integer perform(Game<TwoDimensionalCoordinatesData, Void> game, String data) {
                return null;
            }
        };
    }
}
