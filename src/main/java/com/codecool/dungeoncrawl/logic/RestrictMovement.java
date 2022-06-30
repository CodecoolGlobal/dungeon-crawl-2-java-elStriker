package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.PlayerInventory;
import com.codecool.dungeoncrawl.data.actors.Actor;

//refactor the class
public class RestrictMovement {
    public boolean isValidMove(Cell nextCell, GameMap map) {
        PlayerInventory inventory = new PlayerInventory();
        InventoryService inventoryService = new InventoryService(inventory);
        CombatService combatService = new CombatService();
//        return !((nextCell.getTileName().equals("wall")) ||
//                (nextCell.getActor() instanceof Skeleton) ||
//                (nextCell.getTileName().equals("closeddoor")));
        boolean value = false;

        if (nextCell.getTileName().equals("wall")) {}

        else if (nextCell.getTileName().equals("closeddoor")) {
            if (inventoryService.hasKey()) {
                map.getCell(nextCell.getX(), nextCell.getY()).setType(CellType.OPENDOOR);
                value = true;
            }
        }else if (nextCell.getActor() instanceof Actor){
            combatService.exchangeBlows(nextCell.getActor(), map.getPlayer());
        }
        return value;
    }
}
