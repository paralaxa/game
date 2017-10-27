package sk.stopangin.service.impl;

import sk.stopangin.field.ActionData;
import sk.stopangin.field.ActionField;
import sk.stopangin.field.Field;
import sk.stopangin.game.Game;
import sk.stopangin.movement.Coordinates;
import sk.stopangin.movement.Movement;
import sk.stopangin.movement.TwoDimensionalCoordinatesData;

public class ActionServiceImpl {


    public Integer performAction(Game<TwoDimensionalCoordinatesData, Void> game) {
        ActionData<Integer> actionData = getActionData(game);
        return actionData.getAction().perform(game);
    }

    public ActionData<Integer> getActionData(Game<TwoDimensionalCoordinatesData, Void> game) {
        Movement<TwoDimensionalCoordinatesData> movement = game.getActiveRound().getMovement();
        Coordinates<TwoDimensionalCoordinatesData> currentRoundPosition = movement.getNewPosition();
        Field<TwoDimensionalCoordinatesData> fieldForCoordinates = game.getBoard().getFieldForCoordinates(currentRoundPosition);
        if (fieldForCoordinates instanceof ActionField) {
            return ((ActionField<TwoDimensionalCoordinatesData, Integer>) fieldForCoordinates).getActionData();
        }
        return null;
    }

}
