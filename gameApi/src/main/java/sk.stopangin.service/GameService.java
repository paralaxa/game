package sk.stopangin.service;

import sk.stopangin.board.Board;
import sk.stopangin.field.Action;
import sk.stopangin.game.Round;

import java.io.Serializable;

public interface GameService<T extends Serializable, R, A extends Serializable> {

    Long startGame(String playerName);


    Round<T, R> commitRound(Long gameId, T coordinatesData);


    Board<T> getBoard(Long gameId);


    A performAction(Long gameId, String data);


    Action<A, T, R> getActionForCurrentRound( Long gameId);
}
