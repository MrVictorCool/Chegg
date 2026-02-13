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
    int scale, mouseX, mouseY;
    Vector2i clickPressed, clickReleased;
    Vector2i start = null;

    /**
     *Amount of "virtual" pixels of distance where the click button might be released to still count as clicking
     */
    final static int CLICK_PIXEL_LENIENCY = 3;

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        
        cursor.setCoordinates(board.worldToCoordinate(gp.screenToWorld(mouseX, mouseY)));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        
        cursor.setCoordinates(board.worldToCoordinate(gp.screenToWorld(mouseX, mouseY)));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // System.out.print("Click: ");
        // // if (start == null) {
        // //     start = cursor.getCoordinates();
        // //     System.out.println("Starting point: " + start);
        // // } else {
        // //     System.out.println("Destination: " + cursor.getCoordinates());
        // //     gameManager.move(start, cursor.getCoordinates());
        // //     start = null;
        // // }
        // if (cursor.isInBoard()) {
        //     gameManager.handleClickAt(cursor.getCoordinates());
        // }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int code = e.getButton();
        if (code == MouseEvent.BUTTON1) {
            clickPressed = gp.screenToWorld(mouseX, mouseY);
        } else if (code == MouseEvent.BUTTON2) {
            System.out.println(board.worldToCoordinate(gp.screenToWorld(mouseX, mouseY)));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int code = e.getButton();
        if (code == MouseEvent.BUTTON1) {
            clickReleased = gp.screenToWorld(mouseX, mouseY);
            if (clickPressed.distanceTo(clickReleased) <= CLICK_PIXEL_LENIENCY) {
                doMouseClick();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

    private void doMouseClick() {
        System.out.print("Click: ");
        // if (start == null) {
        //     start = cursor.getCoordinates();
        //     System.out.println("Starting point: " + start);
        // } else {
        //     System.out.println("Destination: " + cursor.getCoordinates());
        //     gameManager.move(start, cursor.getCoordinates());
        //     start = null;
        // }
        if (cursor.isInBoard()) {
            gameManager.handleClickAt(cursor.getCoordinates());
        }
    }

    public MouseHandler(Cursor cursor, GamePanel gp, Board board, GameManager gameManager) {
        this.cursor = cursor;
        this.gp = gp;
        this.board = board;
        this.gameManager = gameManager;
    }
}
