package com.codecool.dungeoncrawl.logic.InventoryService;

import com.codecool.dungeoncrawl.data.PlayerInventory;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

public class InventoryService {
    PlayerInventory playerInventory;

    public InventoryService(PlayerInventory inventory) {
        this.playerInventory = inventory;
    }

    public void pickUpItem(Item item) {
        playerInventory.playerInventory.add(item.toString());
    }
}
