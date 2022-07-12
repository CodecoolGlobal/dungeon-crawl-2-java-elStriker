package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.logic.InventoryService;

public class Pickaxe extends Item {
    public Pickaxe(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "pickaxe";
    }

    @Override
    public void pickUp(InventoryService inventoryService) {
        inventoryService.pickUpPickaxe(this);
    }
}
