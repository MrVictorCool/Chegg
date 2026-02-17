package main.tools;

import tile.Board;

public class Vector2i {
    public int x;
    public int y;

    public static final Vector2i ZERO = new Vector2i();
    public static final Vector2i UP = new Vector2i(0, -1);
    public static final Vector2i DOWN = new Vector2i(0, 1);
    public static final Vector2i LEFT = new Vector2i(-1, 0);
    public static final Vector2i RIGHT = new Vector2i(1, 0);

    public Vector2i() {
        this(0, 0);
    }

    public Vector2i(Vector2i v2) {
        this(v2.x, v2.y);
    }

    public Vector2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }

    public Vector2i add(int dX, int dY) {
        return new Vector2i(x + dX, y + dY);
    }
    
    public Vector2i add(Vector2i v2) {
        return add(v2.x, v2.y);
    }

    public double getAngle() {
        return Math.atan((double)x / y);
    }

    public Vector2i inverse() {
        return new Vector2i(-x, -y);
    }

    public Vector2 toVector2() {
        return new Vector2(x, y);
    }

    public boolean equals(Vector2i v2) {
        if (v2.x != x || v2.y != y) {return false;}
        return true;
    }

    public double distanceTo(Vector2i v2i) {
        return this.toVector2().distanceTo(v2i.toVector2());
    }

    public double distanceTo(Vector2i origin, Vector2i destination) {
        return origin.distanceTo(destination);
    }

    /**
     * Checks if the {@code Vector2i} is contained within the bound of [0, 0] to the specified non negative coordinates in form of a {@code Vector2i}.
     * @param v2 {@code Vector2i} use as the upper non negative bound
     */
    public boolean isOnBound(Vector2i v2) {
        if (x <= v2.x && y <= v2.y && x >= 0 && y >= 0) {return true;}
        return false;
    }

    /**
     * Checks if the {@code Vector2i} is contained within the bound of [0, 0] to the specified non negative coordinates.
     * In this implementation of the method it uses the size of a {@code Board} since is a common enough operation within this project.
     * @param board Uses the {@code Board}'s dimensions as bounds (which must be a rectangle)
     */
    public boolean isOnBound(Board board) {
        return isOnBound(new Vector2i(board.board.length - 1, board.board[0].length - 1));
    }

    /**
     * Checks if the {@code Vector2i} is contained within the bound of {@code topLeft} to {@code bottomRight}.
     * @param topLeft The top left corner, both x and y should be less or equal to {@code bottomRight}.
     * @param bottomRight The top right corner, both x and y should be greater or equal to {@code topLeft}.
     */
    public boolean isOnBound(Vector2i topLeft, Vector2i bottomRight) {
        System.out.println(this + " " + topLeft + " " + bottomRight);
        if (x > bottomRight.x || y > bottomRight.y || x < topLeft.x || y < topLeft.y) {System.out.println("false");return false;}
        System.out.println("true");return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector2i)) {
            return false;
        }

        Vector2i v = (Vector2i) obj;

        return equals(v);
    }
}
