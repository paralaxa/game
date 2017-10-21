package sk.stopangin.service;

import sk.stopangin.board.Board;
import sk.stopangin.board.RandomSimpleGameBoardGenerator;
import sk.stopangin.game.Game;
import sk.stopangin.game.Round;
import sk.stopangin.game.SimpleGame;
import sk.stopangin.movement.*;
import sk.stopangin.piece.LinearMovingPiece;
import sk.stopangin.piece.Piece;
import sk.stopangin.player.HumanPlayer;
import sk.stopangin.player.Player;

import java.util.*;

//todo into api module
public class GameService {
    //    private GameRepository gameRepository;

    public Game startGame() {
        Game<Integer> game = new SimpleGame();
        Board board = RandomSimpleGameBoardGenerator.generate();
        List<Player> players = new ArrayList<>();

        Set<MovementType> movementTypes = new HashSet<>();
        movementTypes.add(new LinearMovementType());

        Player p1 = new HumanPlayer();
        p1.setName("Player1");
        Piece piece1 = new LinearMovingPiece("p1", new LinearCoordinates(0), movementTypes);
        piece1.setId(1l);
        Set<Piece> pieces1 = new HashSet<>();
        pieces1.add(piece1);
        p1.setPieces(pieces1);

        Player p2 = new HumanPlayer();
        p2.setName("Player2");
        Piece piece2 = new LinearMovingPiece("p2", new LinearCoordinates(0), movementTypes);
        Set<Piece> pieces2 = new HashSet<>();
        piece2.setId(2l);
        pieces2.add(piece2);
        p2.setPieces(pieces2);

        players.addAll(Arrays.asList(p1, p2));

        game.startGame(board, players, p1);
        return game;
    }

    public Round<Integer> createNextRound(Game game) {
        return game.createNexRound();
    }

    public void commitRound(Game game, Movement<Integer> movement) {
        game.commitRound(movement);
        game.createNexRound();
    }

    public static void main(String[] args) {
        GameService gameService = new GameService();
        Game game = gameService.startGame(); //ziskat z game repo
        gameService.createNextRound(game);
        Movement<Integer> movement = new Movement<>();
        movement.setNewPocition(new LinearCoordinates(3));
        movement.setMovementType(new LinearMovementType());
        Piece p = new LinearMovingPiece();
        p.setId(1l);
        movement.setPiece(p);
        gameService.commitRound(game, movement);
    }

}
