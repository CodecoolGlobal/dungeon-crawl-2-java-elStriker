package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.logic.InventoryService;

public class Sword extends Item{
    public Sword(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "sword";
    }

    @Override
    public void pickUp(InventoryService inventoryService) {
        inventoryService.pickUpSword(this);
    }
}
