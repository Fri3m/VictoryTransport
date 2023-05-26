package com.example.victorytransport;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
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
       PublicMethods publicMethods=new PublicMethods();
        //Creating panes and texts
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
        bottomPane.setStyle("-fx-background-color: YELLOW ");
        borderPane1.setBottom(bottomPane);


         //Creating buttons
        //NewGame button and car animation
        Button newGameButton = new Button("New Game");
        newGameButton.setMaxWidth(400);
        newGameButton.setMaxHeight(50);
        newGameButton.setMinWidth(400);
        newGameButton.setMinHeight(50);

        newGameButton.setLayoutX(200);
        newGameButton.setLayoutY(100);

        newGameButton.setOnAction(e -> {
            LevelFX levelFX=new LevelFX(new File("level1.txt"));
            try {
                levelFX.start(stage);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Line carLine=new Line();
        carLine.setStartX(-100);
        carLine.setStartY(50);
        carLine.setEndX(900);
        carLine.setEndY(50);
        carLine.setVisible(false);
        Image carImage=new Image(new File("car.png").toURI().toString());
        ImageView carImageView=new ImageView(carImage);
        carImageView.setFitWidth(150);
        carImageView.setPreserveRatio(true);

        PathTransition carPath= publicMethods.createPathTransitionsForMainMenu(carLine,carImageView);
        carPath.setCycleCount(PathTransition.INDEFINITE);
        carPath.play();
        //Load button and van animation
        Button loadGameButton = new Button("Load Game");
        loadGameButton.setMaxWidth(400);
        loadGameButton.setMaxHeight(50);
        loadGameButton.setMinWidth(400);
        loadGameButton.setMinHeight(50);

        loadGameButton.setLayoutX(200);
        loadGameButton.setLayoutY(250);

        loadGameButton.setOnAction(e -> {
            LevelFX levelFX=new LevelFX(new File("saveFile.txt"));
            try {
                levelFX.start(stage);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Line vanLine=new Line();
        vanLine.setStartX(-100);
        vanLine.setStartY(200);
        vanLine.setEndX(900);
        vanLine.setEndY(200);
        vanLine.setVisible(false);

        Image vanImage=new Image(new File("van.png").toURI().toString());
        ImageView vanImageView=new ImageView(vanImage);
        vanImageView.setFitWidth(150);
        vanImageView.setPreserveRatio(true);

        PathTransition vanPath= publicMethods.createPathTransitionsForMainMenu(vanLine,vanImageView);
        vanPath.setCycleCount(PathTransition.INDEFINITE);
        vanPath.play();
        //Exit button and bus animation
        Button exitButton = new Button("Exit");
        exitButton.setMaxWidth(400);
        exitButton.setMaxHeight(50);
        exitButton.setMinWidth(400);
        exitButton.setMinHeight(50);

        exitButton.setLayoutX(200);
        exitButton.setLayoutY(400);

        exitButton.setOnAction(e -> System.exit(0));

        Line busLine=new Line();
        busLine.setStartX(-100);
        busLine.setStartY(350);
        busLine.setEndX(900);
        busLine.setEndY(350);
        busLine.setVisible(false);

        Image busImage=new Image(new File("bus.png").toURI().toString());
        ImageView busImageView=new ImageView(busImage);
        busImageView.setFitWidth(150);
        busImageView.setPreserveRatio(true);

        PathTransition busPath= publicMethods.createPathTransitionsForMainMenu(busLine,busImageView);
        busPath.setCycleCount(PathTransition.INDEFINITE);
        busPath.play();

        bottomPane.getChildren().addAll(newGameButton, loadGameButton, exitButton,carLine,carImageView,vanLine,vanImageView,busLine,busImageView);

        stage.setScene(scene);
        stage.show();
    }
}
