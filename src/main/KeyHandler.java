package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.tools.Vector2i;
import tile.Board;

public class KeyHandler implements KeyListener {

    Board board;
    GameManager gameManager;
    
    public KeyHandler(Board board, GameManager gameManager) {
        this.board = board;
        this.gameManager = gameManager;
    }

    @Override
    public void keyTyped(KeyEvent e) {} //unused

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_SPACE) {
           board.clearHighlights();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // int code = e.getKeyCode();
    }
    
}
