package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Guardian extends Actor{

    private final static int INITIAL_HEALTH = 9999999;
    private static final int INITIAL_STRENGTH = 999999;

    public Guardian(Cell cell) {
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
        return "guardian";
    }
}
