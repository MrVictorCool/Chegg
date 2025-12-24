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
        return "Team: " + name;
    }
    
}
