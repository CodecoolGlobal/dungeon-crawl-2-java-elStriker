package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;
import com.codecool.dungeoncrawl.logic.InventoryService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Item implements Drawable {
    private Cell cell;
    private ItemType itemType;
    private final int strength;
    public boolean isPickedUp() {
        return isPickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        isPickedUp = pickedUp;
    }

    private boolean isPickedUp;

    protected Item(Cell cell) {
        this.cell = cell;
        this.cell.setItem(this);
        isPickedUp = false;
        strength = 0;
    }

    @Override
    public String getTileName() {
        return itemType.getTileName();
    }

    public void pickUp(InventoryService inventoryService){
        inventoryService.pickUpGenericItem(this);
    }

    public int getStrength() {
        return strength;
    }
}
