package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.tools.Vector2i;
import tile.Board;

public class MouseHandler implements MouseListener, MouseMotionListener{

    Cursor cursor;
    GamePanel gp;
    Board board;
    GameManager gameManager;
    int scale;
    Vector2i start = null;

    @Override
    public void mouseDragged(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        
        cursor.setCoordinates(board.worldToCoordinate(gp.screenToWorld(mouseX, mouseY)));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        
        cursor.setCoordinates(board.worldToCoordinate(gp.screenToWorld(mouseX, mouseY)));

        // System.out.println(board.worldToCoordinate(gp.screenToWorld(mouseX, mouseY)));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.print("Click: ");
        if (start == null) {
            start = cursor.getCoordinates();
            System.out.println("Starting point: " + start);
        } else {
            System.out.println("Destination: " + cursor.getCoordinates());
            gameManager.move(start, cursor.getCoordinates());
            start = null;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int code = e.getButton();
        if (code == MouseEvent.BUTTON1) {
            System.out.println("Click registered");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int code = e.getButton();
        if (code == MouseEvent.BUTTON1) {
            System.out.println("Click release registered");
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

    public MouseHandler(Cursor cursor, GamePanel gp, Board board, GameManager gameManager) {
        this.cursor = cursor;
        this.gp = gp;
        this.board = board;
        this.gameManager = gameManager;
    }
}
