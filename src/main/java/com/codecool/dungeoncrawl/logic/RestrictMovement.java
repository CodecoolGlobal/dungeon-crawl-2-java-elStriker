package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Skeleton;

//refactor the class
public class RestrictMovement {
    public boolean isValidMove(Cell nextCell) {
//        return !((nextCell.getTileName().equals("wall")) ||
//                (nextCell.getActor() instanceof Skeleton) ||
//                (nextCell.getTileName().equals("closeddoor")));
        boolean value = false;

        if (nextCell.getTileName().equals("wall"))
        {
            return value;
        } else if (nextCell.getTileName().equals("closeddoor")) {


        } else if (nextCell.getActor() instanceof Actor){

        }
    }
}
