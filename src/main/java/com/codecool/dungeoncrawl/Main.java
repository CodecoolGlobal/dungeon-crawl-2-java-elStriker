package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameInformation;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.data.actors.*;
import com.codecool.dungeoncrawl.data.PlayerInventory;
import com.codecool.dungeoncrawl.logic.InventoryService;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.logic.MovementService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.SQLException;

import static com.sun.javafx.application.PlatformImpl.exit;

public class Main extends Application {
    private int level = 1;
    GameMap map = MapLoader.loadMap(level);
    MovementService movementService = new MovementService();
    Canvas canvas = new Canvas(
            Math.min(map.getWidth(), 30) * Tiles.TILE_WIDTH,
            Math.min(map.getHeight(), 22) * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    GameDatabaseManager dbManager;
    Label playerStrength = new Label();
    PlayerInventory inventory = new PlayerInventory();
    InventoryService inventoryService = new InventoryService(inventory);
    GridPane ui = new GridPane();
    BorderPane borderPane = new BorderPane();
    Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Label label = new Label(BEGINNING_TEXT);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().setAll(label);

        Scene scene1 = new Scene(stackPane, 500, 400);
        Stage stage = new Stage();
        stage.setScene(scene1);
        stage.showAndWait();

        ui.setPrefWidth(200);
        ui.setPadding(new Insets(50));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);

        ui.add(new Label("Strength: "), 0, 1);
        ui.add(playerStrength, 1, 1);

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        scene = new Scene(borderPane);
        GameInformation gameInformation = new GameInformation(primaryStage, scene);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void onKeyReleased(KeyEvent keyEvent) {
        KeyCombination exitCombinationMac = new KeyCodeCombination(KeyCode.W, KeyCombination.SHORTCUT_DOWN);
        KeyCombination exitCombinationWin = new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN);
        if (exitCombinationMac.match(keyEvent)
                || exitCombinationWin.match(keyEvent)
                || keyEvent.getCode() == KeyCode.ESCAPE) {
            exit();
        }
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                Player player = map.getPlayer();
                Guardian guardian = map.getGuardian();
                movementService.move(player, inventoryService, 0, -1);
                moveGhost();
                if (level == 3){
                    moveGuardian(guardian, player);
                }
                changeLevel(player);
                refresh();
                break;
            case DOWN:
                player = map.getPlayer();
                guardian = map.getGuardian();
                movementService.move(player, inventoryService, 0, 1);
                moveGhost();
                if (level == 3){
                    moveGuardian(guardian, player);
                }
                changeLevel(player);
                refresh();
                break;
            case LEFT:
                player = map.getPlayer();
                guardian = map.getGuardian();
                movementService.move(player, inventoryService, -1 , 0);
                moveGhost();
                if (level == 3){
                    moveGuardian(guardian, player);
                }
                changeLevel(player);
                refresh();
                break;
            case RIGHT:
                player = map.getPlayer();
                guardian = map.getGuardian();
                movementService.move(player, inventoryService,1, 0);
                moveGhost();
                if (level == 3){
                    moveGuardian(guardian, player);
                }
                changeLevel(player);
                refresh();
                break;
            case F:
                player = map.getPlayer();
                Cell playerCell = (map.getCell(player.getX(), player.getY()));
                Item item = playerCell.getItem();
                inventoryService.pickUpItem(player ,item);
                refresh();
                break;
            case J:
                player = map.getPlayer();
                Cell cell = player.getCell();
                Cell nextDown = cell.getNeighbor(0, 1);
                Cell nextUp = cell.getNeighbor(0, -1);
                if (nextDown.getType() == CellType.BLOCK && inventoryService.hasPickaxe()){
                    nextDown.setType(CellType.FLOOR);
                } else if (nextUp.getType() == CellType.BLOCK && inventoryService.hasPickaxe()) {
                    nextUp.setType(CellType.FLOOR);
                }
                refresh();
                break;
        }
    }

    private void moveGuardian(Guardian guardian, Player player) {
        if (guardian.getX() > player.getX()){
            movementService.moveGuardian(guardian, player, -1, 0);
        } else if (guardian.getX() < player.getX()){
            movementService.moveGuardian(guardian, player, 1, 0);
        } else {
            if (guardian.getY() > player.getY()) {
                movementService.moveGuardian(guardian, player, 0, -1);
            } else {
                movementService.moveGuardian(guardian, player, 0, 1);
            }
        }

    }

    private void changeLevel(Player player) {
        if (player.getCell().getType() == CellType.OPENDOOR) {
            level++;
            rebuildGui();
        } else if (player.getCell().getType() == CellType.BACKDOOR) {
            level--;
            rebuildGui();
        }

    }

    private void rebuildGui() {
        int health = map.getPlayer().getHealth();
        int strength = map.getPlayer().getStrength();
        map = MapLoader.loadMap(level);
        canvas = new Canvas(
                Math.min(map.getWidth(), 30) * Tiles.TILE_WIDTH,
                Math.min(map.getHeight(), 22) * Tiles.TILE_WIDTH);
        context = canvas.getGraphicsContext2D();

        borderPane = new BorderPane();
        borderPane.setCenter(canvas);
        borderPane.setRight(ui);
        scene = new Scene(borderPane);
        refresh();

        map.getPlayer().setHealth(health);
        map.getPlayer().setStrength(strength);

        GameInformation.primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        GameInformation.primaryStage.setTitle("Dungeon Crawl");
        GameInformation.primaryStage.show();
    }

    //ui
    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        int centerX = (int)(canvas.getWidth()/(Tiles.TILE_WIDTH*2));
        int centerY = (int)(canvas.getHeight()/(Tiles.TILE_WIDTH*2)) -1;
        int[] shift = new int[2];
        if (map.getPlayer().getX() > centerX){
            shift[0] = map.getPlayer().getX() - centerX;
        }
        if (map.getPlayer().getY() > centerY){
            shift[1] = map.getPlayer().getY() - centerY;
        }

        for (int x = 0; x+shift[0] < map.getWidth(); x++) {
            for (int y = 0; y+shift[1] < map.getHeight(); y++) {
                Cell cell = map.getCell(x+shift[0], y+shift[1]);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.getItem() != null) {
                    if (!cell.getItem().isPickedUp()) {
                        Tiles.drawTile(context, cell.getItem(), x, y);
                    } else {
                        Tiles.drawTile(context, cell, x, y);
                    }
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        playerStrength.setText("" + map.getPlayer().getStrength());
        for (int i = 0; i < inventory.playerInventory.size(); i++) {
            Item itemName = inventory.playerInventory.get(i);
            Label playerInventory = new Label();
            playerInventory.setText(itemName.getTileName());
            ui.add(playerInventory, 0, i + 3);
            }
        }
        private void moveGhost() {
            if (map.getGhostCount() > 0) {
                for (int i = 0; i < map.getGhostCount(); i++){
                    Ghost ghost = GameMap.ghosts.get(i);
                    movementService.moveEnemy(ghost, map);
                }
            }
        }
        String BEGINNING_TEXT = "You open your eyes. Your sight is met with a never-ending untamed wilderness.\n" +
                "This must be hell - you realize. You can vaguely remember getting shot\n" +
                "by your girlfriend's jealous cousin.\n" +
                "In that moment of realization, another important thought has crossed your mind.\n" +
                "\n" +
                "If I am dead, my wife will freely browse through my phone and see all of my dms.\n" +
                "I CANNOT LET THAT HAPPEN. \n" +
                "You get up from the ground and gather every fiber of energy\n" +
                "to embark on a journey through hell. Will you be able to escape hells claws or will\n" +
                "you succumb to it's treacheries like countless other souls roaming this plain? \n\n\n" +
                "Close the window to start the game!";
     }
    /*}

    private void setupDbManager() {
        dbManager = new GameDatabaseManager();
        try {
            dbManager.setup();
        } catch (SQLException ex) {
            System.out.println("Cannot connect to database.");
        }
    }

    private void exit() {
        try {
            stop();
        } catch (Exception e) {
            System.exit(1);
        }
        System.exit(0);
    }
    }*/

