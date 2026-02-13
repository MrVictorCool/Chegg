package main;

import java.awt.Color;

public class Team {
    public String name;
    public Color color;

    public Team(String name, Color color) {
        this.name = name;
        this.color = color;
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
    
}
