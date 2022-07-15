package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.actors.Ghost;
import com.codecool.dungeoncrawl.data.actors.Guardian;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.items.Key;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(int level) {
        InputStream is = MapLoader.class.getResourceAsStream("/map" + level + ".txt");
        System.out.println("/map" + level + ".txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        System.out.println(width + "," + height);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    char character = line.charAt(x);
                    switch (character) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                        case 'x':
                        case 'ä':
                            cell.setType(CellType.FLOOR);
                            EnemyService.createSkeleton(cell, character);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new Key(cell);
                            break;
                        case 'd':
                            cell.setType(CellType.ClOSEDDOOR);
                            break;
                        case 'g':
                            Ghost ghost = new Ghost(cell);
                            map.setGhost(ghost);
                            GameMap.ghosts.add(ghost);
                            map.setGhostCount(map.getGhostCount() + 1);
                            break;
                        case 'r':
                            cell.setType(CellType.RIVER);
                            break;
                        case 'w':
                            cell.setType(CellType.ROCK);
                            break;
                        case 'c':
                            cell.setType(CellType.CACTUS);
                            break;
                        case 't':
                            cell.setType(CellType.TREE);
                            break;
                        case 'f':
                            cell.setType(CellType.FIRE);
                            break;
                        case 'b':
                            cell.setType(CellType.BRIDGE);
                            break;
                        case 'ü':
                            cell.setType(CellType.BLOCK);
                            break;
                        case 'o':
                            cell.setType(CellType.BACKDOOR);
                            break;
                        case '+':
                            cell.setType(CellType.OPENDOOR);
                            break;
                        case 'q':
                            cell.setType(CellType.FLOOR);
                            Guardian guardian = new Guardian(cell);
                            map.setGuardian(guardian);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
