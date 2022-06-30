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
    }
}
