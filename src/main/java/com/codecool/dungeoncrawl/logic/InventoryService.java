package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.PlayerInventory;
import com.codecool.dungeoncrawl.data.items.*;

import java.util.Map;

public class InventoryService {
    PlayerInventory playerInventory;
    boolean hasKey = false;
    boolean hasSword = false;
    boolean hasPickaxe = false;

    //Map<ItemType, Integer> inventory;

    public InventoryService(PlayerInventory inventory) {
        this.playerInventory = inventory;
        updateItems();
    }

    private void updateItems() {
        for (Item item : playerInventory.playerInventory) {

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

    public void pickUpItem(Item item) {
        item.pickUp(this);
    }

    public void pickUpGenericItem(Item item) {
        if (!item.isPickedUp()){
            playerInventory.playerInventory.add(item);
            item.setPickedUp(true);
            updateItems();
        }
    }

    public void pickUpKey(Key key) {
        pickUpGenericItem(key);
        this.hasKey = true;
    }

    public void pickUpSword(Sword sword) {
        pickUpGenericItem(sword);
        this.hasSword = true;
    }

    public void pickUpPickaxe(Pickaxe pickaxe){
        pickUpGenericItem(pickaxe);
        this.hasPickaxe = true;
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
