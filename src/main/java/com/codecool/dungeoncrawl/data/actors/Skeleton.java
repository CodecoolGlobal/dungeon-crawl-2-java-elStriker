package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Skeleton extends Actor {
    private final int INITIAL_HEALTH = 10;
    private final int INITIAL_STRENGTH = 5;

    private char skeletonName;
    public Skeleton(Cell cell, char skeletonName) {
        super(cell);
        this.skeletonName = skeletonName;
    }

    public char getSkeletonName() {
        return skeletonName;
    }

    public void setSkeletonName(char skeletonName) {
        this.skeletonName = skeletonName;
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
