package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.actors.Actor;

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
            if (nextCell.getActor() != null) {
                combatService.exchangeBlows(player, nextCell.getActor());
            } else if (nextCell.getType() == CellType.ClOSEDDOOR && inventory.hasKey()) {
                    nextCell.setType(CellType.OPENDOOR);
            }
        }
    }
}