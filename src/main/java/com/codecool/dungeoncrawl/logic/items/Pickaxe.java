package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Pickaxe extends Item {
    public Pickaxe(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "pickaxe";
    }
}
