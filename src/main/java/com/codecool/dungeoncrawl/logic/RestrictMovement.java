package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.actors.Skeleton;

//refactor the class
public class RestrictMovement {
    public boolean isValidMove(Cell nextCell) {
        return !((nextCell.getTileName().equals("wall")) ||
                (nextCell.getActor() instanceof Skeleton) ||
                (nextCell.getTileName().equals("closeddoor")));
    }
}
