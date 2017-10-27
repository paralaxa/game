package sk.stopangin.field;

import sk.stopangin.game.Game;

import java.io.Serializable;

public interface Action<A extends Serializable> {

    <T extends Serializable, R> A perform(Game<T, R> game);

    <T extends Serializable, R> A perform(Game<T, R> game, String data);
}
