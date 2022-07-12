package com.codecool.dungeoncrawl.data;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),

    ClOSEDDOOR("closeddoor"),

    OPENDOOR("opendoor"),

    PICKAXE("pickaxe"),

    KEY("key"),
    GHOST("ghost");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
