package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.logic.InventoryService;
import lombok.Getter;
import lombok.Setter;

public class Sword extends Item{
    public Sword(Cell cell) {
        super(cell);
    }
    private final int strength = 5;
    @Override
    public String getTileName() {
        return "sword";
    }

    @Override
    public void pickUp(InventoryService inventoryService) {
        inventoryService.pickUpSword(this);
    }
    @Override
    public int getStrength() {
        return strength;
    }
}
