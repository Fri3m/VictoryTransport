//150121046 Ömer Can Şimşek
//150121044 Ömer Yıldırım
//150121038 Batuhan Kurt

//This is simple javaFx level program. This class brings everything together and creates the level.

package com.example.victorytransport;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import package1.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


public class LevelFX extends Application {

    public LevelFX() {
        levelFile = new File("level1.txt");
        level = new Level(levelFile);
        levelNumber = calculateLevelNumber(level);
    }

    public LevelFX(File levelFile) {
        this.levelFile = levelFile;
        this.level = new Level(levelFile);
        levelNumber = calculateLevelNumber(level);
    }

    PublicMethods publicMethods = new PublicMethods();
    File levelFile;
    Level level;

    String whereIsVehicle = "Istanbul";
    AtomicReference<String> toWhere = new AtomicReference<>("");
    ArrayList<Line> lines = new ArrayList<>();
    ArrayList<Circle> circleImages = new ArrayList<>();

    int cost, levelNumber = 1;
    double score;

    public int calculateLevelNumber(Level level) {
        Level level1 = new Level(new File("level1.txt"));
        Level level2 = new Level(new File("level2.txt"));
        Level level3 = new Level(new File("level3.txt"));
        Level level4 = new Level(new File("level4.txt"));
        Level level5 = new Level(new File("level5.txt"));

        ArrayList<Level> levels = new ArrayList<>();
        levels.add(level1);
        levels.add(level2);
        levels.add(level3);
        levels.add(level4);
        levels.add(level5);
        boolean isOK = true;
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 101; i++) {
                if (level.levelMap[i] == null && levels.get(j).levelMap[i] == null) {
                    isOK = true;
                } else if (level.levelMap[i] != null && levels.get(j).levelMap[i] != null) {
                    if (level.levelMap[i] instanceof Vehicle && levels.get(j).levelMap[i] instanceof Vehicle)
                        isOK = true;
                    else if (level.levelMap[i] instanceof City && (levels.get(j).levelMap[i] instanceof City))
                        isOK = true;
                    else if (level.levelMap[i] instanceof Fixed && levels.get(j).levelMap[i] instanceof Fixed)
                        isOK = true;

                } else {
                    isOK = false;
                    break;
                }
            }
            if (isOK) {
                return j+1;
            } else {
                isOK = true;
            }

        }
        return 0;
    }

    public void start(Stage stage) throws IOException {
        stage.getIcons().add(new Image(new File("images/icon.png").toURI().toString()));

        BorderPane borderPane1 = new BorderPane();
        Scene scene = new Scene(borderPane1, 800, 600);
        stage.setResizable(false);
        stage.setTitle("Victory Transport");
        stage.setScene(scene);
        stage.show();

        //Right Side of Scene start
        BorderPane rightBorderPane = new BorderPane();
        rightBorderPane.setMinSize(200, 600);
        rightBorderPane.setMaxSize(200, 600);
        rightBorderPane.setStyle("-fx-background-color: #ffb700 ");
        borderPane1.setRight(rightBorderPane);
        BorderPane borderPaneTop = new BorderPane();
        rightBorderPane.setTop(borderPaneTop);


        //Score Label Start
        Label scoreLabel = new Label("Score: " + score);
        borderPaneTop.setLeft(scoreLabel);
        //Score Label End


        //Level Label Start
        if (levelNumber > 0 && levelNumber < 6) {
            Label levelLabel = new Label("Level: " + levelNumber);
            levelLabel.setText("Level: " + levelNumber);
            borderPaneTop.setRight(levelLabel);
        }
        //Level Label End

        //Menu Button start
        MenuButton menuButton = new MenuButton("Menu");
        menuButton.setMaxSize(200, 30);
        menuButton.setMinSize(200, 30);
        borderPaneTop.setTop(menuButton);

        MenuItem menuNextLevel = new MenuItem("Next Level");
        if ((levelNumber > 0) && (levelNumber < 5)) {
            menuNextLevel.setDisable(true);
            menuNextLevel.setOnAction(e -> {
                String whichLevelx = "level" + (levelNumber + 1) + ".txt";
                LevelFX levelFX = new LevelFX(new File(whichLevelx));
                try {
                    levelFX.start(stage);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            menuButton.getItems().add(menuNextLevel);
        } else {

        }

        SaveLoad saveLoad = new SaveLoad();

        MenuItem menuSave = new MenuItem("Save");
        menuSave.setOnAction(e -> {
            saveLoad.save(level,score);
        });

        MenuItem menuLoad = new MenuItem("Load");
        menuLoad.setOnAction(e -> {
            LevelFX levelFX=new LevelFX(new File("saveFile.txt"));
            levelFX.score=saveLoad.loadScore();
            try {
                levelFX.start(stage);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        menuButton.getItems().addAll(menuSave, menuLoad);
        //Menu Button end

        //Center TextFlow start
        BorderPane centerBorderPane = new BorderPane();

        BorderPane topBorderPane = new BorderPane();
        topBorderPane.setMinSize(200, 200);
        topBorderPane.setMaxSize(200, 200);

        TextFlow clickedCityTextFlow = new TextFlow();
        clickedCityTextFlow.setMinSize(200, 100);
        clickedCityTextFlow.setMaxSize(200, 100);
        Text clickedCityText = new Text("Clicked City: ");
        clickedCityTextFlow.getChildren().add(clickedCityText);
        topBorderPane.setTop(clickedCityTextFlow);


        TextFlow currentCityTextFlow = new TextFlow();
        currentCityTextFlow.setMinSize(200, 100);
        currentCityTextFlow.setMaxSize(200, 100);
        Text currentCityText = new Text("Current City: ");
        currentCityTextFlow.getChildren().add(currentCityText);
        topBorderPane.setBottom(currentCityTextFlow);


        centerBorderPane.setTop(topBorderPane);

        TextFlow actionText = new TextFlow();
        actionText.setMinSize(200, 65);
        actionText.setMaxSize(200, 65);
        Text actionsText = new Text("Last Action: ");
        actionText.getChildren().add(actionsText);
        actionText.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        centerBorderPane.setCenter(actionText);

        TextFlow vehicleTextFlow = new TextFlow();
        vehicleTextFlow.setMinSize(200, 235);
        vehicleTextFlow.setMaxSize(200, 235);
        Text vehicleText = new Text("Vehicle Information:");
        vehicleTextFlow.getChildren().add(vehicleText);
        centerBorderPane.setBottom(vehicleTextFlow);

        ((Vehicle) level.levelMap[0]).startEngine((City) level.levelMap[publicMethods.returnCityCell(level.levelMap, ((Vehicle) level.levelMap[0]).getCellID())]); // change the city id for each level
        whereIsVehicle = publicMethods.returnCityName(level.levelMap, ((Vehicle) level.levelMap[0]).getCellID());
        currentCityText.setText(publicMethods.currentCityInformation(level, whereIsVehicle));

        vehicleText.setText("Vehicle Information:\nTotal capacity :" + ((Vehicle) level.levelMap[0]).getPassengerCapacity()
                + "\nTotal passenger : " + ((Vehicle) level.levelMap[0]).getTotalPassenger()
                + "\nEmpty seat: " + ((Vehicle) level.levelMap[0]).getEmptySpace());
        for (Passenger passengerObj : ((Vehicle) level.levelMap[0]).getPassengerList()) {
            String cityName = publicMethods.returnCityNameWithCityID(level.levelMap, passengerObj.getDestinationCityID());
            vehicleText.setText(vehicleText.getText() + "\n" + passengerObj.getNumberOfPassengers() + " passenger wants to go " + cityName);
        }


        rightBorderPane.setCenter(centerBorderPane);
        //Center TextFlow end

        //Left Side of Scene
        Pane pane1 = new Pane();
        pane1.setPrefSize(600, 600);
        pane1.setStyle("-fx-background-color: #1d1e31");
        borderPane1.setLeft(pane1);

        //Vehicle start
        Image carImage;
        if (((Vehicle) level.levelMap[0]).getPassengerCapacity() < 6) {
            carImage = new Image(new File("car.png").toURI().toString());
        } else if (((Vehicle) level.levelMap[0]).getPassengerCapacity() >= 6 && ((Vehicle) level.levelMap[0]).getPassengerCapacity() < 14) {
            carImage = new Image(new File("van.png").toURI().toString());
        } else {
            carImage = new Image(new File("bus.png").toURI().toString());
        }

        ImageView carImageView = new ImageView(carImage);

        carImageView.setFitWidth(90);
        carImageView.setPreserveRatio(true);
        carImageView.setX((((Vehicle) level.levelMap[0]).getCellID() - 1) % 10 * 60 + 50);
        carImageView.setY((((Vehicle) level.levelMap[0]).getCellID() - 1) / 10 * 60 + 50);
        pane1.getChildren().add(carImageView);
        //Vehicle end

        //City Text start
        for (int i = 1; i < 101; i++) {
            if (level.levelMap[i] instanceof City) {
                int a = ((City) level.levelMap[i]).getName().length();
                Text cityText = new Text(((City) level.levelMap[i]).getName());
                cityText.setFill(Color.YELLOW);
                double x = (((City) level.levelMap[i]).getCellID() - 1) % 10 * 60 + 10;
                double y = (((City) level.levelMap[i]).getCellID() - 1) / 10 * 60 + 70;

                if (a > 7) {
                    while (a > 7) {
                        x -= 1.6;
                        a--;
                    }
                } else {
                    while (a < 7) {
                        x += 1.6;
                        a++;
                    }
                }
                cityText.setLayoutX(x);
                cityText.setLayoutY(y);
                cityText.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 13));
                cityText.setTextAlignment(TextAlignment.CENTER);
                pane1.getChildren().add(cityText);
            }
        }
        //City Text end

        //Fixed start
        for (int i = 0; i < 101; i++) {
            if (level.levelMap[i] instanceof Fixed) {
                Circle circle = publicMethods.drawCircle(((Fixed) level.levelMap[i]).getCellID());
                Text text = publicMethods.drawFixedText(((Fixed) level.levelMap[i]).getCellID());
                pane1.getChildren().addAll(circle, text);
            }
        }


        //Fixed end

        //Drive button start
        Button driveButton = new Button("Drive");
        driveButton.setMaxSize(200, 60);
        driveButton.setMinSize(200, 60);
        driveButton.setDisable(true);
        rightBorderPane.setBottom(driveButton);


        //images start
        imagesCreate(pane1, clickedCityText, driveButton);
        //images end


        driveButton.setOnAction(e -> {
            for (int i = 0; i < circleImages.size(); i++) {
                circleImages.get(i).setDisable(true);
            }
            driveButton.setDisable(true);
            PublicMethods forVariables = new PublicMethods();
            int startCellID = 0, endCellID = 0;
            if (!toWhere.get().equals("")) {
                startCellID = publicMethods.returnCityCell(level.levelMap, whereIsVehicle);
                endCellID = publicMethods.returnCityCell(level.levelMap, toWhere.get());
            }

            City startCity = (City) level.levelMap[startCellID];
            City endCity = (City) level.levelMap[endCellID];
            forVariables = ((Vehicle) level.levelMap[0]).drive(endCity);
            Vehicle vehicle = (Vehicle) level.levelMap[0];
            actionsText.setText("Last action:\n" + whereIsVehicle + " to " + toWhere.get() + " \n" + forVariables.enterTheVehicle + " passenger enter the vehicle\n" + forVariables.exitTheVehicle + " passenger exit the vehicle");
            vehicleText.setText("Vehicle Information:\nTotal capacity :" + ((Vehicle) level.levelMap[0]).getPassengerCapacity()
                    + "\nTotal passenger : " + ((Vehicle) level.levelMap[0]).getTotalPassenger()
                    + "\nEmpty seat: " + ((Vehicle) level.levelMap[0]).getEmptySpace());
            cost = publicMethods.calculatingDistance(publicMethods.returnCityCell(level.levelMap, whereIsVehicle), publicMethods.returnCityCell(level.levelMap, toWhere.get()));
            score += forVariables.income - cost;
            scoreLabel.setText("Score: " + Math.round(score));
            for (Passenger passengerObj : ((Vehicle) level.levelMap[0]).getPassengerList()) {
                String cityName = publicMethods.returnCityNameWithCityID(level.levelMap, passengerObj.getDestinationCityID());
                vehicleText.setText(vehicleText.getText() + "\n" + passengerObj.getNumberOfPassengers() + " passenger wants to go " + cityName);
            }

            whereIsVehicle = toWhere.get();
            toWhere.set("");

            currentCityText.setText(publicMethods.currentCityInformation(level, whereIsVehicle));
            clickedCityText.setText("Clicked City: ");
            //Creating animations
            List<PathTransition> pathTransitions = publicMethods.createPathTransitions(lines, carImageView, publicMethods.returnCityCell(level.levelMap, whereIsVehicle));

            pathTransitions.get(0).play();
            pathTransitions.get(0).setCycleCount(1);

            for (int i = 0; i < pathTransitions.size(); i++) {
                int nextIndex = (i + 1) % pathTransitions.size();
                PathTransition currentTransition = pathTransitions.get(i);
                PathTransition nextTransition = pathTransitions.get(nextIndex);
                currentTransition.setOnFinished(event -> nextTransition.play());
            }
            pathTransitions.get(pathTransitions.size() - 1).setOnFinished(event -> {
                for (PathTransition transition : pathTransitions) {
                    transition.stop();
                }
            });
            Timeline timeline = new Timeline();
            KeyFrame keyFrame = new KeyFrame(Duration.millis(2500));
            timeline.getKeyFrames().add(keyFrame);
            timeline.setOnFinished(finishedEvent -> {
                for(Line line : lines){
                    line.setVisible(false);
                }
                driveButton.setDisable(false);
                for (int i = 0; i < circleImages.size(); i++) {
                    circleImages.get(i).setDisable(false);
                }
            });
            timeline.play();
            boolean hasPassenger = false;
            for(int i = 0; i < 101; i++) {
                if(level.levelMap[i] instanceof City) {
                    if(((City) level.levelMap[i]).getPassengerList().size() > 0) {
                        hasPassenger = true;
                        break;
                    }
                }
            }
            if((((Vehicle)level.levelMap[0]).getPassengerList().size()==0) && !hasPassenger){
                menuNextLevel.setDisable(false);
                Pane finishPane = new Pane();
                Text finishText = new Text("Level Finished");
                finishText.setLayoutX(125);
                finishText.setLayoutY(75);
                finishText.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
                finishText.setFill(Color.BLACK);

                Stage finishStage = new Stage();
                Scene finishScene = new Scene(finishPane, 500, 150);
                finishStage.setScene(finishScene);
                finishStage.setResizable(false);
                finishPane.getChildren().add(finishText);
                finishStage.setTitle("Level Finished");
                finishStage.show();
            }

        });
        //Drive button end
        //Right Side of Scene end
    }

    public void imagesCreate(Pane pane1, Text clickedCityText, Button driveButton) {
        for (int i = 0; i < 101; i++) {
            if (level.levelMap[i] instanceof City) {
                circleImages.add(publicMethods.createCircleImageView(((City) level.levelMap[i]).getName(), level.levelMap));
            }
        }
        for (Circle circle : circleImages) {
            if (!pane1.getChildren().contains(circle))
                pane1.getChildren().add(circle);
        }
        for (int i = 0; i < circleImages.size(); i++) {
            int cellID = (int) ((circleImages.get(i).getCenterX() - 30) / 60 + 1 + (circleImages.get(i).getCenterY() - 30) / 60 * 10);
            String cityName = publicMethods.returnCityName(level.levelMap, cellID);
            circleImages.get(i).setOnMouseClicked(e -> {
                buttonsClicked(cityName, pane1, clickedCityText, driveButton);
                pane1.getChildren().removeAll(circleImages);
                imagesCreate(pane1, clickedCityText, driveButton);
            });
        }
    }

    public void buttonsClicked(String cityName, Pane pane1, Text clickedCityText, Button driveButton) {

        if (toWhere.get().equals(cityName) || whereIsVehicle.equals(cityName)) {
            clickedCityText.setText("Clicked City: ");
            toWhere.set("");
            driveButton.setDisable(true);
            for (Line line : lines) {
                line.setVisible(false);
                pane1.getChildren().remove(line);
            }
            lines.clear();
        } else {
            driveButton.setDisable(false);
            for (Circle circle : circleImages) {
                circle.setVisible(false);
                pane1.getChildren().remove(circle);
            }
            int distance = publicMethods.calculatingDistance(publicMethods.returnCityCell(level.levelMap, whereIsVehicle), publicMethods.returnCityCell(level.levelMap, cityName));
            clickedCityText.setText("Clicked City:  " + cityName + "\nDistance : " + distance);
            for (Passenger passengerObj : ((City) level.levelMap[publicMethods.returnCityCell(level.levelMap, cityName)]).getPassengerList()) {
                clickedCityText.setText(clickedCityText.getText() + "\n" + passengerObj.getNumberOfPassengers() + " passenger wants to go " + publicMethods.returnCityNameWithCityID(level.levelMap, passengerObj.getDestinationCityID()));
            }
            toWhere.set(cityName);

            pane1.getChildren().removeAll(lines);
            lines.clear();

            lines = publicMethods.drawLines(level.levelMap, publicMethods.returnCityCell(level.levelMap, whereIsVehicle), publicMethods.returnCityCell(level.levelMap, toWhere.get()));

            for (Line line : lines) {
                if (!pane1.getChildren().contains(line))
                    pane1.getChildren().add(line);
            }

            for (int j = 0; j < 101; j++) {
                if (level.levelMap[j] instanceof City) {
                    circleImages.add(publicMethods.createCircleImageView(((City) level.levelMap[j]).getName(), level.levelMap));
                }
            }
            for (Circle circle : circleImages) {
                if (!pane1.getChildren().contains(circle))
                    pane1.getChildren().add(circle);
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
