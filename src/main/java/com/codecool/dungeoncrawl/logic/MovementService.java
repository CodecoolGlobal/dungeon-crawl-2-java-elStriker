package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Ghost;
import com.codecool.dungeoncrawl.util.RNG;

public class MovementService {
    CombatService combatService = new CombatService();
    RestrictMovement movement = new RestrictMovement();
    RNG rng = new RNG();
    public void move(Actor player, InventoryService inventory, int dx, int dy) {
        Cell cell = player.getCell();
        Cell nextCell = cell.getNeighbor(dx, dy);
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

    public void moveEnemy(Actor actor, GameMap map) {
        Cell cell = actor.getCell();
        if (actor instanceof Ghost) {
            if (rng.getRandomNumber(4) == 0) {
                Cell nextCell = cell.getNeighbor(0, 1);    //Down
                moveGhost(actor, map, cell, nextCell);
            } else if (rng.getRandomNumber(4) == 1) {
                Cell nextCell = cell.getNeighbor(0, -1);    //Up
                moveGhost(actor, map, cell, nextCell);
            } else if (rng.getRandomNumber(4) == 2) {
                Cell nextCell = cell.getNeighbor(-1, 0);    //Left
                moveGhost(actor, map, cell, nextCell);
            } else if (rng.getRandomNumber(4) == 1) {
                Cell nextCell = cell.getNeighbor(1, 0);    //Right
                moveGhost(actor, map, cell, nextCell);
            }
        }
    }

    private void moveGhost(Actor actor, GameMap map, Cell cell, Cell nextCell) {
        if (movement.isValidMove(nextCell) && nextCell.getX() <
                map.getWidth() -1 && nextCell.getY() < map.getHeight() -1) {
            cell.setActor(null);
            nextCell.setActor(actor);
            cell = nextCell;
            actor.setCell(cell);
        } else moveEnemy(actor, map);
    }
}