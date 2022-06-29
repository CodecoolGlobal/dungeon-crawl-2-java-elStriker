package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Actor {
    private int health = 10;
    public Skeleton(Cell cell) {
        super(cell);
    }

    @Override
    public int getInitialHealth() {
        return 10;
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
