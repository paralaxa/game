package sk.stopangin.repository;

import org.springframework.stereotype.Component;
import sk.stopangin.game.Game;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GameRepositoryImpl {
    private Map<Long, Game> storage = new ConcurrentHashMap<>();

    public Game save(Game game) {
        return storage.put(game.getId(), game);
    }

    public Game getById(Long id) {
        Game game = storage.get(id);
        if (game == null) {
            throw new GameRepositoryException("Unable to find game by id:" + id);
        }
        return game;
    }

    public void remove(Game game) {
        storage.remove(game);
    }

    public void remove(Long id) {
        remove(getById(id));
    }

}
