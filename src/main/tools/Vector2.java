package main.tools;

public class Vector2 {
    public double x;
    public double y;

    public static final Vector2 ZERO = new Vector2();
    public static final Vector2 UP = new Vector2(0, -1);
    public static final Vector2 DOWN = new Vector2(0, 1);
    public static final Vector2 LEFT = new Vector2(-1, 0);
    public static final Vector2 RIGHT = new Vector2(1, 0);

    public Vector2() {
        this(0, 0);
    }

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }

    public void add(double dX, double dY) {
        x += dX;
        y+= dY;
    }
    
    public void add(Vector2i v2) {
        add(v2.x, v2.y);
    }
    public void add(Vector2 v2) {
        add(v2.x, v2.y);
    }

    public double getAngle() {
        return Math.atan((double)x / y);
    }

    public Vector2 scale(Double scale) {
        return new Vector2(x * scale, y * scale);
    }

    public Vector2 inverse() {
        return new Vector2(-x, -y);
    }

    public Vector2i toVector2i() {
        return new Vector2i((int) x, (int) y);
    }

    public Vector2i toVector2iRound() {
        return new Vector2i((int) Math.round(x), (int) Math.round(y));
    }

    public double distanceTo(Vector2 v2) {
        return Math.pow(Math.pow(x - v2.x, 2) + Math.pow(y - v2.y, 2), 0.5);
    }

    public double distanceTo(Vector2 origin, Vector2 destination) {
        return origin.distanceTo(destination);
    }

    public boolean equals(Vector2 v2) {
        if (v2.x != x || v2.y != y) {return false;}
        return true;
    }
}
