package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Ghost extends Actor{
    private static final int INITIAL_HEALTH = 999999;
    private final static int INITIAL_STRENGTH = 0;

    public Ghost(Cell cell) {
        super(cell);
    }

    @Override
    public int getInitialHealth() {
        return INITIAL_HEALTH;
    }

    @Override
    public int getInitialStrength() {
        return INITIAL_STRENGTH;
    }

    @Override
    public String getTileName() {
        return "ghost";
    }
}
