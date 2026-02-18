package main;

import java.awt.Color;

import egg.Egg;

public class Team {
    public String name;
    public Color color;
    public int index;
    public Egg king;

    public Team(String name, Color color, int index) {
        this.name = name;
        this.color = color;
        this.index = index;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean equals(Team t) {
        if (t.name.equals(this.name)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Team)) {
            return false;
        }

        Team t = (Team) obj;

        return equals(t);
    }

    public void setKing(Egg egg) {
        if (king != null) {
            king.king = false;
        }
        king = egg;
        king.king = true;
    }
    
}
