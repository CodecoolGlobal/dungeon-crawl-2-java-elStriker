package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Skeleton;
import com.codecool.dungeoncrawl.data.items.Pickaxe;
import com.codecool.dungeoncrawl.data.items.Sword;

public class CombatService {
    Skeleton skeleton;
    public void exchangeBlows(Actor fighter1, Actor fighter2) {
        int fighter1Power = fighter1.getStrength();
        int fighter2Power = fighter2.getStrength();
        int player1Health = fighter1.getHealth();
        int fighter2Health = fighter2.getHealth();
        fighter1.setHealth(player1Health - fighter2Power);
        fighter2.setHealth(fighter2Health - fighter1Power);
        if (hasDied(fighter1)) {
            fighter1.getCell().setActor(null);
        }
        if (hasDied(fighter2)) {
            fighter2.getCell().setActor(null);
        }
    }

    public void dropItem(Actor actor){
        Cell cell;
        if (actor instanceof Skeleton){
            cell = actor.getCell();
            if (((Skeleton) actor).getSkeletonName() == 'x'){
                new Pickaxe(cell);
            } else if (((Skeleton) actor).getSkeletonName() == 's') {
                System.out.println("I am S!");
                new Sword(cell);
            }
        }
    }

    public boolean hasDied(Actor actor) {
        if (actor.getHealth() < 1) {
            dropItem(actor);
            return true;
        }
        return false;
    }
}
