package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;
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

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    abstract public int getInitialHealth();
    abstract public int getInitialStrength();
}
