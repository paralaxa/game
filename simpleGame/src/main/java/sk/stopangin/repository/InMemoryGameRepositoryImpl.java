package sk.stopangin.repository;

import org.springframework.stereotype.Repository;
import sk.stopangin.game.Game;
import sk.stopangin.movement.TwoDimensionalCoordinatesData;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryGameRepositoryImpl implements GameRepository<TwoDimensionalCoordinatesData, Void> {
    private Map<Long, Game<TwoDimensionalCoordinatesData, Void>> storage = new ConcurrentHashMap<>();

    public Long save(Game<TwoDimensionalCoordinatesData, Void> game) {
        game.setId(generateNewGameId());
        storage.put(game.getId(), game);
        return game.getId();
    }

    public Game<TwoDimensionalCoordinatesData, Void> getById(Long id) {
        Game<TwoDimensionalCoordinatesData, Void> game = getByIdSafe(id);
        if (game == null) {
            throw new GameRepositoryException("Unable to find game by id:" + id);
        }
        return game;
    }

    private Game<TwoDimensionalCoordinatesData, Void> getByIdSafe(Long id) {
        return storage.get(id);
    }

    public void remove(Game<TwoDimensionalCoordinatesData, Void> game) {
        storage.remove(game);
    }

    public void remove(Long id) {
        remove(getById(id));
    }

    private Long generateNewGameId() {
        Long id = 1l;
        while (getByIdSafe(id) != null) {
            id++;
        }
        return id;
    }

}
