package com.codecool.dungeoncrawl.logic.services;

import com.codecool.dungeoncrawl.data.PlayerInventory;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.data.items.Key;
import com.codecool.dungeoncrawl.data.items.Pickaxe;
import com.codecool.dungeoncrawl.data.items.Sword;

public class InventoryService {
    PlayerInventory playerInventory;
    boolean hasKey = false;
    boolean hasSword = false;
    boolean hasPickaxe = false;

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

            if (item instanceof Sword){
                hasSword = true;
            }

            if (item instanceof Pickaxe){
                hasPickaxe = true;
            }
        }
    }

    public boolean hasPickaxe(){
        return hasPickaxe;
    }

    public boolean hasSword(){
        return hasSword;
    }

    public boolean hasKey() {
        return hasKey;
    }
}
