package com.codecool.dungeoncrawl.data;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),

    ClOSEDDOOR("closeddoor"),

    OPENDOOR("opendoor"),

    PICKAXE("pickaxe"),

    KEY("key"),
    GHOST("ghost"),
    RIVER("river"),
    ROCK("rock"),
    FIRE("fire"),
    BRIDGE("bridge"),
    TREE("tree"),
    CACTUS("cactus");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
