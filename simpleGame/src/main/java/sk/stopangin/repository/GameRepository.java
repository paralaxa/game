package sk.stopangin.repository;

import sk.stopangin.game.Game;

import java.io.Serializable;

public interface GameRepository <T extends Serializable,R>{
    Long save(Game<T,R> game);

    Game<T,R> getById(Long id);

    void remove(Game<T,R> game);

    void remove(Long id);
}
