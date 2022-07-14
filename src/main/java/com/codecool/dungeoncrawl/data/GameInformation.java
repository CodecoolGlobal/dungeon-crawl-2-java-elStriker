package com.codecool.dungeoncrawl.data;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameInformation {
    public static Stage primaryStage;
    public static Scene scene;
    public GameInformation(Stage primaryStage, Scene scene) {
        this.primaryStage = primaryStage;
        this.scene = scene;
    }
}
