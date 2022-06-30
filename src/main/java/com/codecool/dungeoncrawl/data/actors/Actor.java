package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.RestrictMovement;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public abstract class Actor implements Drawable {

    private int strength;
    private Cell cell;
    private int health;


    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
        this.health = getInitialHealth();
        this.strength = getInitialStrength();
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        //move only on empty fields checked by Movement Service
        RestrictMovement movement = new RestrictMovement();
        if (movement.isValidMove(nextCell)) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    abstract public int getInitialHealth();
    abstract public int getInitialStrength();
}
