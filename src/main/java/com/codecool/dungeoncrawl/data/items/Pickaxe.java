package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;

public class Pickaxe extends Item {
    public Pickaxe(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "pickaxe";
    }
}
