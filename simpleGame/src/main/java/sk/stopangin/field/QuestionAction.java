package sk.stopangin.field;

import sk.stopangin.game.Game;

import java.io.Serializable;

public class QuestionAction implements Action<Integer> {
    @Override
    public <T extends Serializable, R> Integer perform(Game<T, R> game) {
        return null;

    }

    @Override
    public <T extends Serializable, R> Integer perform(Game<T, R> game, String data) {
        return null;
    }
}
