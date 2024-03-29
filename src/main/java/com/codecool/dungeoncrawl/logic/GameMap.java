package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.actors.Ghost;
import com.codecool.dungeoncrawl.data.actors.Guardian;
import com.codecool.dungeoncrawl.data.actors.Player;

import java.util.ArrayList;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;
    private Ghost ghost;
    private Guardian guardian;
    private int ghostCount = 0;
    public static ArrayList<Ghost> ghosts = new ArrayList<>();

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setGhost(Ghost ghost) {
        this.ghost = ghost;
    }

    public Ghost getGhost() {
        return ghost;
    }

    public int getGhostCount() {
        return ghostCount;
    }

    public void setGhostCount(int ghostCount) {
        this.ghostCount = ghostCount;
    }

    public void setGuardian(Guardian guardian) {
        this.guardian = guardian;
    }

    public Guardian getGuardian() {
        return guardian;
    }
}
