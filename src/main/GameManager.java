package main;

import java.awt.Color;

import main.tools.Vector2i;
import tile.Board;

public class GameManager {
    
    Board board;
    public Team[] teams;

    public GameManager(Board board) {
        this.board = board;
        teams = new Team[2];
        teams[0] = new Team("Red", Color.decode("#bd0a36"));
        teams[1] = new Team("Blue", Color.decode("#0d638b"));
    }

    public void move(Vector2i start, Vector2i end) {
        board.board[end.x][end.y].egg = board.board[start.x][start.y].egg;
        board.board[start.x][start.y].egg = null;
        board.board[end.x][end.y].update();
        System.out.println("Egg moved: " + board.board[end.x][end.y].egg.name + " " + board.board[end.x][end.y].egg.team);
    }
}
