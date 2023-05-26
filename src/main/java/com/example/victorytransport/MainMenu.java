package com.example.victorytransport;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MainMenu extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane borderPane1 = new BorderPane();
        Scene scene = new Scene(borderPane1, 800, 600);
        stage.setResizable(false);
        stage.setTitle("Victory Transport");
        stage.getIcons().add(new Image(new File("images/icon.png").toURI().toString()));

        Pane topPane = new Pane();
        topPane.setMaxSize(800,100);
        topPane.setMinSize(800,100);
        topPane.setStyle("-fx-background-color: black");
        borderPane1.setTop(topPane);

        Text title = new Text("Victory Transport");
        title.setFill(Color.YELLOW);
        title.setStyle("-fx-font: 50 arial;");
        title.setLayoutX(200);
        title.setLayoutY(60);

        topPane.getChildren().add(title);


        Pane bottomPane = new Pane();
        bottomPane.setMaxSize(800,500);
        bottomPane.setMinSize(800,500);
        bottomPane.setStyle("-fx-background-color: yellow");
        borderPane1.setBottom(bottomPane);



        Button newGameButton = new Button("New Game");
        newGameButton.setMaxWidth(600);
        newGameButton.setMaxHeight(100);
        newGameButton.setMinWidth(600);
        newGameButton.setMinHeight(100);


        newGameButton.setLayoutX(100);
        newGameButton.setLayoutY(100);
        newGameButton.setOnAction(e -> {
            LevelFX levelFX=new LevelFX(new File("level1.txt"));
            try {
                levelFX.start(stage);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Button loadGameButton = new Button("Load Game");
        loadGameButton.setMaxWidth(600);
        loadGameButton.setMaxHeight(100);
        loadGameButton.setMinWidth(600);
        loadGameButton.setMinHeight(100);

        loadGameButton.setLayoutX(100);
        loadGameButton.setLayoutY(230);

        loadGameButton.setOnAction(e -> {
            LevelFX levelFX=new LevelFX(new File("saveFile.txt"));
            try {
                levelFX.start(stage);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Button exitButton = new Button("Exit");
        exitButton.setMaxWidth(600);
        exitButton.setMaxHeight(100);
        exitButton.setMinWidth(600);
        exitButton.setMinHeight(100);

        exitButton.setLayoutX(100);
        exitButton.setLayoutY(360);

        exitButton.setOnAction(e -> System.exit(0));

        bottomPane.getChildren().addAll(newGameButton, loadGameButton, exitButton);

        stage.setScene(scene);
        stage.show();
    }
}
