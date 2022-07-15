package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Player extends Actor {
    final static int INITIAL_STRENGTH = 5;
    final static int INITIAL_HEALTH = 40;

    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }

    @Override
    public int getInitialHealth() {
        return INITIAL_HEALTH;
    }

    @Override
    public int getInitialStrength() {
        return INITIAL_STRENGTH;
    }
}
