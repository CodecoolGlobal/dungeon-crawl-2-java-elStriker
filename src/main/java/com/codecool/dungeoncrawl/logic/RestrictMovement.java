package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Actor;

//refactor the class
public class RestrictMovement {
    public boolean isValidMove(Cell nextCell) {
        return !((nextCell.getTileName().equals("wall")) ||
                (nextCell.getActor() instanceof Actor) ||
                (nextCell.getTileName().equals("closeddoor")) ||
                (nextCell.getTileName().equals("fire")) ||
                (nextCell.getTileName().equals("river")) ||
                (nextCell.getTileName().equals("rock")) ||
                (nextCell.getTileName().equals("cactus")) ||
                (nextCell.getTileName().equals("tree")) ||
                (nextCell.getTileName().equals("block")));
    }
}
