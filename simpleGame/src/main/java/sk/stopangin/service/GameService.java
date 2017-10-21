package sk.stopangin.service;

import sk.stopangin.board.Board;
import sk.stopangin.board.RandomSimpleGameBoardGenerator;
import sk.stopangin.game.Game;
import sk.stopangin.game.SimpleGame;
import sk.stopangin.piece.LinearMovingPiece;
import sk.stopangin.piece.Piece;
import sk.stopangin.player.HumanPlayer;
import sk.stopangin.player.Player;

import java.util.*;

//todo into api module
public class GameService {
    public Game startGame() {
        Game<Integer> game = new SimpleGame();
        Board board = RandomSimpleGameBoardGenerator.generate();
        List<Player> players = new ArrayList<>();

        Player p1 = new HumanPlayer();
        p1.setName("Player1");
        Piece piece1 = new LinearMovingPiece();
        Set<Piece> pieces1 = new HashSet<>();
        pieces1.add(piece1);
        p1.setPieces(pieces1);

        Player p2 = new HumanPlayer();
        p2.setName("Player2");
        Piece piece2 = new LinearMovingPiece();
        Set<Piece> pieces2 = new HashSet<>();
        pieces2.add(piece2);
        p2.setPieces(pieces2);

        players.addAll(Arrays.asList(p1, p2));

        game.startGame(board, players, p1);

        return game;
    }
}
