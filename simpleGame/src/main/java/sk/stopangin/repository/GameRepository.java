package sk.stopangin.repository;

import sk.stopangin.game.Game;

import java.io.Serializable;

public interface GameRepository <A extends Serializable,T extends Serializable,R>{
    Long save(Game<A,T,R> game);

    Game<A,T,R> getById(Long id);

    void remove(Game<A,T,R> game);

    void remove(Long id);
}
