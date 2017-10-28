package sk.stopangin.field.util;

import lombok.AllArgsConstructor;
import sk.stopangin.field.*;
import sk.stopangin.movement.Coordinates;
import sk.stopangin.movement.TwoDimensionalCoordinates;
import sk.stopangin.movement.TwoDimensionalCoordinatesData;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@AllArgsConstructor
public class SimpleGameFieldsGenerator {
    private int max;
    private List<Question> questions;

    public  Set<Field<TwoDimensionalCoordinatesData>> generateFields() {
        Set<Field<TwoDimensionalCoordinatesData>> fields = new HashSet<>();
        Set<TwoDimensionalCoordinatesData> actionFieldsCoordinates = generateRandomQuestionFieldCoordinates();
        for (int i = 1; i < max; i++) {
            for (int j = 1; j < max; j++) {
                TwoDimensionalCoordinatesData currentFieldCoordinatesData = new TwoDimensionalCoordinatesData(i, j);
                Field<TwoDimensionalCoordinatesData> field = getFieldType(currentFieldCoordinatesData, actionFieldsCoordinates);
                Coordinates<TwoDimensionalCoordinatesData> twoDimensionalCoordinatesDataCoordinates =
                        new TwoDimensionalCoordinates(currentFieldCoordinatesData);
                field.setPosition(twoDimensionalCoordinatesDataCoordinates);
                fields.add(field);
            }
        }
        return fields;
    }

    private  Set<TwoDimensionalCoordinatesData> generateRandomQuestionFieldCoordinates() {
        Set<TwoDimensionalCoordinatesData> result = new HashSet<>();
        for (int i = 0; i < questions.size(); i++) {
            TwoDimensionalCoordinatesData twoDimensionalCoordinatesData = new TwoDimensionalCoordinatesData(generateRandomNumberFromTo(1, max), generateRandomNumberFromTo(1, max));
            while (result.contains(twoDimensionalCoordinatesData)) {
                twoDimensionalCoordinatesData = new TwoDimensionalCoordinatesData(generateRandomNumberFromTo(1, max), generateRandomNumberFromTo(1, max));
            }
            result.add(twoDimensionalCoordinatesData);
        }
        return result;
    }

    private  Field<TwoDimensionalCoordinatesData> getFieldType(TwoDimensionalCoordinatesData currentFieldCoordinatesData, Set<TwoDimensionalCoordinatesData> actionFieldsCoordinatesData) {
        for (TwoDimensionalCoordinatesData actionFieldsCoordinatesDatum : actionFieldsCoordinatesData) {
            if (currentFieldCoordinatesData.equals(actionFieldsCoordinatesDatum)) {
                Question question = questions.iterator().next();
                ActionField<Integer, TwoDimensionalCoordinatesData, Void> actionField = new ActionField<>();
                QuestionAction action = new QuestionAction();
                ActionData actionData = new ActionData();
                actionData.setInfo(question.getContent());
                actionData.setData(question.getAnswer());
                action.setScore(question.getScore());
                action.setActionData(actionData);
                actionField.setAction(action);
                questions.remove(question);
                return actionField;
            }
        }
        return new RegularField<>();
    }

    private  int generateRandomNumberFromTo(int from, int to) {
        Random random = new Random();
        return random.nextInt((to - 1) - from + 1) + from;
    }

}
