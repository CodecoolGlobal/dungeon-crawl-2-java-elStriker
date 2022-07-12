package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Skeleton;

public class EnemyService {
    public static void createSkeleton(Cell cell, char character){
        new Skeleton(cell, character);
    }
}
