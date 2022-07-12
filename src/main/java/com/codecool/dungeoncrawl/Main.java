package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.data.Cell;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.SQLException;

import static com.sun.javafx.application.PlatformImpl.exit;
import static java.time.zone.ZoneRulesProvider.refresh;

public class Main extends Application {
    GameMap map = MapLoader.loadMap();
    MovementService movementService = new MovementService();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    GameDatabaseManager dbManager;
    Label playerInventory = new Label();
    PlayerInventory inventory = new PlayerInventory();
    InventoryService inventoryService = new InventoryService(inventory);
    GridPane ui = new GridPane();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(50));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);

        ui.add(new Label("Inventory: "), 0, 1);
        //ui.add(playerInventory, 0, 2);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
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
            //pick up item on key pressed F
            case UP:
                Player player = map.getPlayer();
                movementService.move(player, inventoryService, 0, -1);
                refresh();
                break;
            case DOWN:
                player = map.getPlayer();
                movementService.move(player, inventoryService, 0, 1);
                refresh();
                break;
            case LEFT:
                player = map.getPlayer();
                movementService.move(player, inventoryService, -1 , 0);
                refresh();
                break;
            case RIGHT:
                player = map.getPlayer();
                movementService.move(player, inventoryService,1, 0);
                refresh();
                break;
            case F:
                player = map.getPlayer();
                Cell playerCell = (map.getCell(player.getX(), player.getY()));
                Item item = playerCell.getItem();
                inventoryService.pickUpItem(item);
                refresh();
                break;
        }
    }

    //ui
    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
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
        for (int i = 0; i < inventory.playerInventory.size(); i++) {
            Item itemName = inventory.playerInventory.get(i);
            Label label = new Label();
            label.setText(itemName.getTileName());
            ui.add(label, 0, i + 2);
            }
        }
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

