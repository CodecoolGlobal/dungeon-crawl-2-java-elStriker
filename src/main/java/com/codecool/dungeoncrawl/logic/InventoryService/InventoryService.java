package com.codecool.dungeoncrawl.logic.InventoryService;

import com.codecool.dungeoncrawl.data.PlayerInventory;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;

public class InventoryService {
    PlayerInventory playerInventory;
    boolean hasKey = false;

    public InventoryService(PlayerInventory inventory) {
        this.playerInventory = inventory;
    }

    public void pickUpItem(Item item) {
        if (!item.isPickedUp()){
            playerInventory.playerInventory.add(item);
            item.setPickedUp(true);
            if (item instanceof Key) {
                hasKey = true;
            }
        }
    }

    public boolean hasKey() {
        return hasKey;
    }
}
