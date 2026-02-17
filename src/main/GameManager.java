package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import egg.ChickenEgg;
import egg.Egg;
import egg.PigEgg;
import egg.VillagerEgg;
import egg.EggType;
import main.tools.Vector2i;
import tile.*;
import ui.UI;

public class GameManager {
    
    Board board;
    public Team[] teamList;
    public int currentTeam;
    List<Vector2i> posibleMoves = new ArrayList<>();
    Tile selectedTile;
    Egg selectedEgg;
    Boolean eggSelected = false;
    Vector2i promotionCoordinates;
    enum gameStates{GENERAL, PROMOTION, PAUSE;}
    gameStates gameState = gameStates.GENERAL;

    public GameManager(Board board) {
        this.board = board;
        teamList = new Team[2];
        teamList[0] = new Team("Red", Color.decode("#bd0a36"));
        teamList[1] = new Team("Blue", Color.decode("#0d638b"));
        currentTeam = 0;
    }

    public void handleClickAt(Vector2i v2) {
        switch (gameState) {
            case GENERAL:
                if (v2.isOnBound(board)) {
                    if (eggSelected) {
                        if (posibleMoves.contains(v2)) {
                            move(selectedEgg.getCoordinates(), v2);
                            passTurn();
                            deselect();
                        } else {
                            deselect();
                        }
                    } else {
                        if (board.getEggAt(v2) != null && board.getEggAt(v2).team.equals(teamList[currentTeam])) {
                            select(v2);
                        } else {
                            deselect();
                        }
                    }
                } else {
                    deselect();
                }
                break;
            case PROMOTION:
                System.out.println("Click while in promotion");
                EggType promotionEggType;
                
                if (v2.isOnBound(new Vector2i(9, 0), new Vector2i(10, 1))) {
                    System.out.println("Should go ahead");
                    if (v2.x == 9) {
                        if (v2.y == 0) {
                            promotionEggType = EggType.HORSE;
                        } else {
                            promotionEggType = EggType.PIG;
                        }
                    } else {
                        if (v2.y == 0) {
                            promotionEggType = EggType.SHEEP;
                        } else {
                            promotionEggType = EggType.WANDERING_TRADER;
                        }
                    }

                    board.createEggAt(promotionEggType, board.getEggAt(promotionCoordinates).team, promotionCoordinates);
                    gameState = gameStates.GENERAL;
                    UI.promotionVisible = false;
                }
                break;
            case PAUSE:
                //TODO: implement pause state:
                break;
            default:
                System.out.println("Unhandled gamestate combination for click action");
                break;
        }
        
    }

    public void move(Vector2i start, Vector2i end) {
        board.setEggAt(board.getEggAt(start), end);
        board.setEggAt(null, start);
        System.out.println("Egg moved: " + board.board[end.x][end.y].egg.name + " " + board.board[end.x][end.y].egg.team);

        if (board.getEggAt(end) instanceof ChickenEgg) {
            ChickenEgg chickenEgg = (ChickenEgg) board.getEggAt(end);
            if (chickenEgg.direction.equals(Vector2i.UP) && end.y == 0 || chickenEgg.direction.equals(Vector2i.DOWN) && end.y == 7) {
                gameState = gameStates.PROMOTION;
                UI.promotionVisible = true;
                promotionCoordinates = end;
                System.out.println("Promotion started");
            }
            if (Math.abs(end.y - start.y) == 2) {
                board.enPassantTile = new Vector2i(start.x, start.y + (int) Math.signum(end.y - start.y));
                board.enPassantEggCoordinate = end;
                System.out.println("En Passant tile: " + board.enPassantTile + " Egg: " + board.enPassantEggCoordinate);
                return;
            }
            if (end.equals(board.enPassantTile)) {
                board.setEggAt(null, board.enPassantEggCoordinate);
                board.enPassantTile = new Vector2i(-1, -1);
                board.enPassantEggCoordinate = new Vector2i(-1, -1);
                return;
            }
        }

        board.enPassantTile = new Vector2i(-1, -1);
        board.enPassantEggCoordinate = new Vector2i(-1, -1);

        if (board.getEggAt(end) instanceof VillagerEgg) {
            VillagerEgg endEgg = (VillagerEgg) board.getEggAt(end);
            endEgg.hasMoved = true;
            if (Math.abs(start.distanceTo(end)) < 1.5) {return;}
            
            System.out.println("Castle detected");
            if (end.x == 2) {
                move(new Vector2i(0, end.y), end.add(1, 0));
            } else {
                move(new Vector2i(7, end.y), end.add(-1, 0));
            }
            return;
        }
        if (board.getEggAt(end) instanceof PigEgg) {
            PigEgg endEgg = (PigEgg) board.getEggAt(end);
            endEgg.hasMoved = true;
        }
    }

    public void select(Vector2i v2) {
        eggSelected = true;
        selectedTile = board.board[v2.x][v2.y];
        selectedEgg = board.getEggAt(v2);
        posibleMoves.clear();

        for (Vector2i moveCoordinates : selectedEgg.getMoves(board)) {
            Vector2i move = moveCoordinates;
            if (move.x > -1 && move.x < 8 && move.y > -1 && move.y < 8) {
                posibleMoves.add(move);
            }
        }

        if (posibleMoves.size() == 0) {
            deselect();
            return;
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
