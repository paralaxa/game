package sk.stopangin.field;

import lombok.Data;
import sk.stopangin.game.Game;

import java.io.Serializable;

@Data
public abstract class Action<A extends Serializable, T extends Serializable, R> {
    private ActionData actionData;

    public abstract A perform(Game<T, R> game);

    public abstract A perform(Game<T, R> game, String data);
}
