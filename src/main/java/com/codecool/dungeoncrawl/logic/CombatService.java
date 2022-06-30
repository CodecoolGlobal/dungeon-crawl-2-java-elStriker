package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.actors.Actor;

public class CombatService {
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

    public boolean hasDied(Actor actor) {
        if (actor.getHealth() < 1) {
            return true;
        }
        return false;
    }
}
