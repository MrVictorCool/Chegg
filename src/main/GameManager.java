package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import egg.Egg;
import main.tools.Vector2i;
import tile.*;

public class GameManager {
    
    Board board;
    public Team[] teamList;
    public int currentTeam;
    List<Vector2i> posibleMoves = new ArrayList<>();
    Tile selectedTile;
    Egg selectedEgg;
    Boolean eggSelected = false;

    public GameManager(Board board) {
        this.board = board;
        teamList = new Team[2];
        teamList[0] = new Team("Red", Color.decode("#bd0a36"));
        teamList[1] = new Team("Blue", Color.decode("#0d638b"));
        currentTeam = 0;
    }

    public void handleClickAt(Vector2i v2) {
        if (eggSelected) {
            if (posibleMoves.contains(v2)) {
                move(selectedEgg.getCoordinates(), v2);
                passTurn();
                deselect();
            } else {
                deselect();
            }
        } else {
            if (board.board[v2.x][v2.y].egg != null && board.board[v2.x][v2.y].egg.team.equals(teamList[currentTeam])) {
                select(v2);
            } else {
                deselect();
            }
        }
    }

    public void move(Vector2i start, Vector2i end) {
        board.board[end.x][end.y].egg = board.board[start.x][start.y].egg;
        board.board[start.x][start.y].egg = null;
        board.board[end.x][end.y].update();
        System.out.println("Egg moved: " + board.board[end.x][end.y].egg.name + " " + board.board[end.x][end.y].egg.team);
    }

    public void select(Vector2i v2) {
        eggSelected = true;
        selectedTile = board.board[v2.x][v2.y];
        selectedEgg = board.board[v2.x][v2.y].egg;
        posibleMoves.clear();

        for (Vector2i relativeMove : selectedEgg.getMoves(board)) {
            Vector2i move = relativeMove; //relativeMove.add(selectedEgg.getCoordinates());
            if (move.x > -1 && move.x < 8 && move.y > -1 && move.y < 8) {
                posibleMoves.add(move);
            }
        }

        board.highlight(posibleMoves.toArray(new Vector2i[0]));
        System.out.println(posibleMoves);
    }
    
    public void deselect() {
        eggSelected = false;
        selectedTile = null;
        selectedEgg = null;
        posibleMoves.clear();
        board.clearHighlights();
    }

    private void passTurn() {
        currentTeam = (currentTeam + 1) % teamList.length;
        System.out.println("Current turn is: " + teamList[currentTeam]);
    }
}
