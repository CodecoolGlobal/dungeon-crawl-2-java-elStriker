package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Actor;

public class MovementService {
    CombatService combatService = new CombatService();
    public void move(Actor player, int dx, int dy) {
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
            }
        }
    }
}