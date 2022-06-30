package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Actor {
    private final int INITIAL_HEALTH = 10;
    private final int INITIAL_STRENGTH = 5;
    public Skeleton(Cell cell) {
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
        return "skeleton";
    }
}
