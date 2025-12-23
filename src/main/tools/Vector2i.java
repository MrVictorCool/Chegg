package main.tools;

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

    public void add(int dX, int dY) {
        x += dX;
        y+= dY;
    }
    
    public void add(Vector2i v2) {
        add(v2.x, v2.y);
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
}
