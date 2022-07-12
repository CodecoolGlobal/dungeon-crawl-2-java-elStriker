package com.codecool.dungeoncrawl.util;
import java.util.Random;

public class RNG {
    public int getRandomNumber(int upperLimit) {
        Random random = new Random();
        int randomInt = random.nextInt(upperLimit);
        return randomInt;
    }
}
