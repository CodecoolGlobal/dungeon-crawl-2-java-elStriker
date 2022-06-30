package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.PlayerInventory;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.items.Key;

public class MovementService {
    CombatService combatService = new CombatService();
    public void move(Actor player, InventoryService inventory, int dx, int dy) {
        Cell cell = player.getCell();
        Cell nextCell = cell.getNeighbor(dx, dy);
        RestrictMovement movement = new RestrictMovement();
        if (movement.isValidMove(nextCell)) {
            cell.setActor(null);
            nextCell.setActor(player);
            cell = nextCell;
            player.setCell(cell);
        } else {
            if (nextCell.getActor() instanceof Actor) {
                combatService.exchangeBlows(player, nextCell.getActor());
            } else if (nextCell.getType() == CellType.ClOSEDDOOR) {
                if (inventory.hasKey()) {
                    System.out.println("I AM HERE");
                    nextCell.setType(CellType.OPENDOOR);
                }
            }
        }
    }
}