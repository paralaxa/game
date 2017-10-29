package sk.stopangin.service;

import sk.stopangin.game.Round;

import java.io.Serializable;

public interface GameService<T extends Serializable, R> {


    Long startGame();

    Round<T, R> commitRound(Long gameId,  T coordinatesData);
}
