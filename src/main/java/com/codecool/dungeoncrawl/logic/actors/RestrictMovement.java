package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
//refactor the class
public class RestrictMovement {
    public boolean isValidMove(Cell nextCell) {
        return !((nextCell.getTileName().equals("wall")) ||
                (nextCell.getActor() instanceof Skeleton) ||
                (nextCell.getTileName().equals("closeddoor")));
    }
}
