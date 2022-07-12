package com.codecool.dungeoncrawl.data.items;

public enum ItemType {
    KEY("key"),
    PICKAXE("pickaxe"),
    SWORD("sword");
    private final String tileName;
    ItemType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
