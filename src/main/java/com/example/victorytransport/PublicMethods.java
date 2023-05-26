package com.example.victorytransport;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import package1.City;
import package1.Level;
import package1.Passenger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PublicMethods {
    public int enterTheVehicle, exitTheVehicle;
    public double income;
    int p, startX, startY, endX, endY, vertHori;
    Line line;
    ArrayList<Line> lineList = new ArrayList<>();

    public PublicMethods(){

    }
    public  PublicMethods(int p, int startX, int startY, int endX, int endY, int vertHori, Line line, ArrayList<Line> lineList){
        this.p = p;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.vertHori = vertHori;
        this.line = line;
        this.lineList = lineList;
    }

    public Line drawLineHoriObjToNull(int StartPoint, int endPoint) {
        int starX = (StartPoint % 10) * 60;
        int Y = (StartPoint / 10) * 60 + 30;
        int endX = (endPoint % 10) * 60 + 30;
        Line line = new Line(starX, Y, endX, Y);
        line.setStrokeWidth(5);
        line.setStroke(Color.BLACK);
        return line;
    }

    public Line drawLineHoriObjToObj(int StartPoint, int endPoint) {
        int starX = (StartPoint % 10) * 60;
        int Y = (StartPoint / 10) * 60 + 30;
        int endX = (endPoint % 10) * 60;
        Line line = new Line(starX, Y, endX, Y);
        line.setStrokeWidth(5);
        line.setStroke(Color.BLACK);
        return line;
    }

    public Line drawLineHoriNullToNull(int StartPoint, int endPoint) {
        int starX = (StartPoint % 10) * 60 - 30;
        int Y = (StartPoint / 10) * 60 + 30;
        int endX = (endPoint % 10) * 60 + 30;
        Line line = new Line(starX, Y, endX, Y);
        line.setStrokeWidth(5);
        line.setStroke(Color.BLACK);
        return line;
    }

    public Line drawLineVertObjToNull(int StartPoint, int endPoint) {
        int startY = (StartPoint / 10) * 60;
        int x = (StartPoint % 10) * 60 - 30;
        int endY = (endPoint / 10) * 60 + 30;
        Line line = new Line(x, startY, x, endY);
        line.setStrokeWidth(5);
        line.setStroke(Color.BLACK);
        return line;
    }

    public Line drawLineVertObjToObj(int StartPoint, int endPoint) {
        int startY = (StartPoint / 10) * 60;
        int x = (StartPoint % 10) * 60 + 30;
        int endY = (endPoint / 10) * 60;
        Line line = new Line(x, startY, x, endY);
        line.setStrokeWidth(5);
        line.setStroke(Color.BLACK);
        return line;
    }

    public Line drawLineVertNullToNull(int StartPoint, int endPoint) {
        int startY = (StartPoint / 10) * 60 + 30;
        int x = (StartPoint % 10) * 60 + 30;
        int endY = (endPoint / 10) * 60 + 30;
        Line line = new Line(x, startY, x, endY);
        line.setStrokeWidth(5);
        line.setStroke(Color.BLACK);
        return line;
    }

    public Circle drawCircle(int id) {
        int centerX = (id % 10) * 60 - 30;
        int centerY = (id / 10) * 60 + 30;
        Circle circle1 = new Circle();
        circle1.setRadius(25);
        circle1.setCenterX(centerX);
        circle1.setCenterY(centerY);
        circle1.setFill(Color.WHITE);
        circle1.setStrokeWidth(5);
        circle1.setStroke(Color.RED);
        return circle1;
    }

    public Text drawFixedText(int id) {
        int centerX = (id % 10) * 60 - 30;
        int centerY = (id / 10) * 60 + 30;
        Text text1 = new Text("X");
        text1.setX(centerX - 11);
        text1.setY(centerY + 11);
        text1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        text1.setFill(Color.RED);
        return text1;
    }

    public Circle createCircleImageView(String filename) {
        Circle circle = new Circle();
        Image image = new Image(new File("images/" + filename).toURI().toString());
        circle.setFill(new ImagePattern(image));
        circle.setRadius(30);
        return circle;
    }

    public Circle createCircleImageView(String cityName,Object[] levelMap) {
        Circle circle = new Circle();
        Image image = new Image(new File("images/" + cityName+".png").toURI().toString());
        circle.setFill(new ImagePattern(image));
        circle.setRadius(30);
        circle.setVisible(true);
        City city = (City) levelMap[returnCityCell(levelMap, cityName)];
        circle.setCenterX((city.getCellID()-1)%10*60+30);
        circle.setCenterY((city.getCellID()-1)/10*60+30);
        return circle;
    }

    public Button createCityButton(City city, Pane pane) {
        int x = (city.getCellID() - 1) % 10 * 60;
        int y = city.getCellID() / 10 * 60;

        Text cityNameText = new Text(city.getName());
        cityNameText.setLayoutX(x + 10);
        cityNameText.setLayoutY(y + 75);
        cityNameText.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 13));
        pane.getChildren().add(cityNameText);

        Button button = new Button(city.getName());
        button.setMinSize(60, 60);
        button.setMaxSize(60, 60);

        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setOpacity(0);
        pane.getChildren().add(button);
        return button;
    }

    public ArrayList<PathTransition> createPathTransitions(ArrayList<Line> lines, ImageView car,int startCellID) {
        Duration duration1 = Duration.seconds(1);
        ArrayList<PathTransition> pathTransitions = new ArrayList<>();
        if(lines.isEmpty()){
            return new ArrayList<>();
        }
        else {
            double x = 3.0 / lines.size();
            duration1 = Duration.seconds(x);
        }
        int startCityX = (startCellID - 1) % 10 * 60+30;
        int startCityY = (startCellID - 1) / 10 * 60+30;

        if(lines.get(lines.size()-1).getEndX()==startCityX && lines.get(lines.size()-1).getEndY()==startCityY){
            for(int i=0;i<lines.size();i++){
                PathTransition pathTransition = new PathTransition();
                pathTransition.setDuration(duration1);
                pathTransition.setPath(lines.get(i));
                pathTransition.setNode(car);
                pathTransitions.add(pathTransition);
            }
        }
        else {
            for (int i= lines.size()-1;i>=0;i--) {
                Line newLine=new Line();
                newLine.setStartX(lines.get(i).getEndX());
                newLine.setStartY(lines.get(i).getEndY());
                newLine.setEndX(lines.get(i).getStartX());
                newLine.setEndY(lines.get(i).getStartY());

                PathTransition pathTransition = new PathTransition();
                pathTransition.setDuration(duration1);
                pathTransition.setPath(newLine);
                pathTransition.setNode(car);
                pathTransitions.add(pathTransition);

            }
        }
        return pathTransitions;
    }
    public void startAnimation(PathTransition pathTransition){
        pathTransition.play();
    }

    public void animation(Line line1, ImageView imageView) {

        // Creating path transition objects
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(3));
        pathTransition.setPath(line1);
        pathTransition.setNode(imageView);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(1);
        pathTransition.setAutoReverse(false);

        // Start the animation
        pathTransition.play();
    }

    public void animationReverse(Line line1, ImageView imageView) {
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(3));
        pathTransition.setNode(imageView);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(1);
        pathTransition.setAutoReverse(false);

        Line newLine1 = new Line();
        newLine1.setStrokeWidth(5.0);
        newLine1.setStroke(javafx.scene.paint.Color.BLACK);
        newLine1.setEndX(line1.getStartX());
        newLine1.setStartX(line1.getEndX());
        newLine1.setEndY(line1.getStartY());
        newLine1.setStartY(line1.getEndY());
        pathTransition.setPath(newLine1);
        // Start the animation
        pathTransition.play();
        pathTransition.setOnFinished(e -> {
            line1.setVisible(false);
        });
    }

    public void animation(Line line1, Line line2, ImageView imageView) {
        // Creating path transition objects
        PathTransition pathTransition = new PathTransition();
        PathTransition pathTransition2 = new PathTransition();

        pathTransition.setInterpolator(Interpolator.LINEAR);
        imageView.setRotate(0);

        pathTransition.setDuration(Duration.seconds(1.5));
        pathTransition.setPath(line1);

        Image newImageVan = new Image(new File("VanReverse.png").toURI().toString());
        ImageView newImageViewVan = new ImageView(newImageVan);
        newImageViewVan.setFitWidth(75);
        newImageViewVan.setPreserveRatio(true);

        Image newImageBus = new Image(new File("BusReverse.png").toURI().toString());
        ImageView newImageViewBus = new ImageView(newImageBus);
        newImageViewBus.setFitWidth(75);
        newImageViewBus.setPreserveRatio(true);

        Image newImageCar = new Image(new File("CarReverse.png").toURI().toString());
        ImageView newImageViewCar = new ImageView(newImageCar);
        newImageViewCar.setFitWidth(75);
        newImageViewCar.setPreserveRatio(true);

/*
        if (line1.getStartX() > line1.getEndX()) {
            if (imageView.getImage().getWidth() == 1032) {
                System.out.println("van");
                pathTransition.setNode(newImageViewVan);
            }
            if (imageView.getImage().getWidth() == 1098) {
                System.out.println("bus");
                pathTransition.setNode(newImageViewBus);
            }
            if (imageView.getImage().getWidth() == 360) {
                System.out.println("car");
                pathTransition.setNode(newImageViewCar);
            }
        } else {
//
        }
 */
        pathTransition.setNode(imageView);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(1);
        pathTransition.setAutoReverse(false);

        // Starting the animation
        pathTransition.play();


        pathTransition.setOnFinished(e -> {
            pathTransition2.setDuration(Duration.seconds(1.5));
            if (!(line1.getEndX() == line2.getStartX() && line1.getEndY() == line2.getStartY())) {
                Line newLine1 = new Line();
                newLine1.setStrokeWidth(5.0);
                newLine1.setStroke(javafx.scene.paint.Color.BLACK);
                newLine1.setEndX(line2.getStartX());
                newLine1.setStartX(line2.getEndX());
                newLine1.setEndY(line2.getStartY());
                newLine1.setStartY(line2.getEndY());
                pathTransition2.setPath(newLine1);
            } else {
                pathTransition2.setPath(line2);

            }/*
            if (line2.getStartX() > line2.getEndX()) {
                if (imageView.getImage().getWidth() == 1032) {
                    pathTransition2.setNode(newImageViewVan);
                }
                if (imageView.getImage().getWidth() == 1098) {
                    pathTransition2.setNode(newImageViewBus);
                }
                if (imageView.getImage().getWidth() == 360) {
                    pathTransition2.setNode(newImageViewCar);
                }
            } else {

            }
*/
            pathTransition2.setNode(imageView);
            pathTransition2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition2.setCycleCount(1);
            pathTransition2.setAutoReverse(false);
            pathTransition2.play();
        });
        pathTransition2.setOnFinished(e -> {
            line1.setVisible(false);
            line2.setVisible(false);
        });
    }

    public void animation(Line line1, Line line2, Line line3, ImageView imageView) {

        // Creating path transition objects
        PathTransition pathTransition = new PathTransition();
        PathTransition pathTransition2 = new PathTransition();
        PathTransition pathTransition3 = new PathTransition();


        pathTransition.setDuration(Duration.seconds(1));
        pathTransition.setPath(line1);
        pathTransition.setNode(imageView);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(1);
        pathTransition.setAutoReverse(false);

        // Starting the animation
        pathTransition.play();

        pathTransition.setOnFinished(e -> {
            pathTransition2.setDuration(Duration.seconds(1));

            if (!(line1.getEndX() == line2.getStartX() && line1.getEndY() == line2.getStartY())) {
                Line newLine1 = new Line();
                newLine1.setStrokeWidth(5.0);
                newLine1.setStroke(javafx.scene.paint.Color.BLACK);
                newLine1.setEndX(line2.getStartX());
                newLine1.setStartX(line2.getEndX());
                newLine1.setEndY(line2.getStartY());
                newLine1.setStartY(line2.getEndY());
                pathTransition2.setPath(newLine1);
            } else {
                pathTransition2.setPath(line2);
            }
            pathTransition2.setNode(imageView);
            pathTransition2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition2.setCycleCount(1);
            pathTransition2.setAutoReverse(false);
            pathTransition2.play();
        });
        pathTransition2.setOnFinished(e -> {
            pathTransition3.setDuration(Duration.seconds(1));
            if (!(line2.getEndX() == line3.getStartX() && line2.getEndY() == line3.getStartY())) {
                Line newLine1 = new Line();
                newLine1.setStrokeWidth(5.0);
                newLine1.setStroke(javafx.scene.paint.Color.BLACK);
                newLine1.setEndX(line3.getStartX());
                newLine1.setStartX(line3.getEndX());
                newLine1.setEndY(line3.getStartY());
                newLine1.setStartY(line3.getEndY());
                pathTransition3.setPath(newLine1);
            } else {
                pathTransition3.setPath(line3);
            }
            pathTransition3.setNode(imageView);
            pathTransition3.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition3.setCycleCount(1);
            pathTransition3.setAutoReverse(false);
            pathTransition3.play();
        });
        pathTransition3.setOnFinished(e -> {
            line1.setVisible(false);
            line2.setVisible(false);
            line3.setVisible(false);
        });
    }

    public void animation(Line line1, Line line2, Line line3, Line line4, ImageView imageView) {

        // Creating path transition objects
        PathTransition pathTransition = new PathTransition();
        PathTransition pathTransition2 = new PathTransition();
        PathTransition pathTransition3 = new PathTransition();
        PathTransition pathTransition4 = new PathTransition();

        pathTransition.setDuration(Duration.seconds(0.75));
        pathTransition.setPath(line1);
        pathTransition.setNode(imageView);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(1);
        pathTransition.setAutoReverse(false);

        // Starting the animation
        pathTransition.play();

        pathTransition.setOnFinished(e -> {
            pathTransition2.setDuration(Duration.seconds(0.75));

            if (!(line1.getEndX() == line2.getStartX() && line1.getEndY() == line2.getStartY())) {
                Line newLine1 = new Line();
                newLine1.setStrokeWidth(5.0);
                newLine1.setStroke(javafx.scene.paint.Color.BLACK);
                newLine1.setEndX(line2.getStartX());
                newLine1.setStartX(line2.getEndX());
                newLine1.setEndY(line2.getStartY());
                newLine1.setStartY(line2.getEndY());
                pathTransition2.setPath(newLine1);
            } else {
                pathTransition2.setPath(line2);
            }

            pathTransition2.setNode(imageView);
            pathTransition2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition2.setCycleCount(1);
            pathTransition2.setAutoReverse(false);
            pathTransition2.play();
        });
        pathTransition2.setOnFinished(e -> {
            pathTransition3.setDuration(Duration.seconds(0.75));

            if (!(line2.getEndX() == line3.getStartX() && line2.getEndY() == line3.getStartY())) {
                Line newLine1 = new Line();
                newLine1.setStrokeWidth(5.0);
                newLine1.setStroke(javafx.scene.paint.Color.BLACK);
                newLine1.setEndX(line3.getStartX());
                newLine1.setStartX(line3.getEndX());
                newLine1.setEndY(line3.getStartY());
                newLine1.setStartY(line3.getEndY());
                pathTransition3.setPath(newLine1);
            } else {
                pathTransition3.setPath(line3);
            }

            pathTransition3.setNode(imageView);
            pathTransition3.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition3.setCycleCount(1);
            pathTransition3.setAutoReverse(false);
            pathTransition3.play();
        });
        pathTransition3.setOnFinished(e -> {

            pathTransition4.setDuration(Duration.seconds(0.75));
            if (!(line3.getEndX() == line4.getStartX() && line3.getEndY() == line4.getStartY())) {
                Line newLine1 = new Line();
                newLine1.setStrokeWidth(5.0);
                newLine1.setStroke(javafx.scene.paint.Color.BLACK);
                newLine1.setEndX(line4.getStartX());
                newLine1.setStartX(line4.getEndX());
                newLine1.setEndY(line4.getStartY());
                newLine1.setStartY(line4.getEndY());
                pathTransition4.setPath(newLine1);
            } else {
                pathTransition4.setPath(line4);
            }

            pathTransition4.setNode(imageView);
            pathTransition4.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition4.setCycleCount(1);
            pathTransition4.setAutoReverse(false);
            pathTransition4.play();
        });
        pathTransition4.setOnFinished(e -> {
            line1.setVisible(false);
            line2.setVisible(false);
            line3.setVisible(false);
            line4.setVisible(false);

        });

    }

    public void animation(Line line1, Line line2, Line line3, Line line4, Line line5, ImageView imageView) {

        // Creating path transition objects
        PathTransition pathTransition = new PathTransition();
        PathTransition pathTransition2 = new PathTransition();
        PathTransition pathTransition3 = new PathTransition();
        PathTransition pathTransition4 = new PathTransition();
        PathTransition pathTransition5 = new PathTransition();

        pathTransition.setDuration(Duration.seconds(0.6));
        pathTransition.setPath(line1);
        pathTransition.setNode(imageView);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(1);
        pathTransition.setAutoReverse(false);

        // Start the animation
        pathTransition.play();

        pathTransition.setOnFinished(e -> {

            pathTransition2.setDuration(Duration.seconds(0.6));
            if (!(line1.getEndX() == line2.getStartX() && line1.getEndY() == line2.getStartY())) {
                Line newLine1 = new Line();
                newLine1.setStrokeWidth(5.0);
                newLine1.setStroke(javafx.scene.paint.Color.BLACK);
                newLine1.setEndX(line2.getStartX());
                newLine1.setStartX(line2.getEndX());
                newLine1.setEndY(line2.getStartY());
                newLine1.setStartY(line2.getEndY());
                pathTransition2.setPath(newLine1);
            } else {
                pathTransition2.setPath(line2);
            }

            pathTransition2.setNode(imageView);
            pathTransition2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition2.setCycleCount(1);
            pathTransition2.setAutoReverse(false);
            pathTransition2.play();
        });

        pathTransition2.setOnFinished(e -> {
            pathTransition3.setDuration(Duration.seconds(0.6));

            if (!(line2.getEndX() == line3.getStartX() && line2.getEndY() == line3.getStartY())) {
                Line newLine1 = new Line();
                newLine1.setStrokeWidth(5.0);
                newLine1.setStroke(javafx.scene.paint.Color.BLACK);
                newLine1.setEndX(line3.getStartX());
                newLine1.setStartX(line3.getEndX());
                newLine1.setEndY(line3.getStartY());
                newLine1.setStartY(line3.getEndY());
                pathTransition3.setPath(newLine1);
            } else {
                pathTransition3.setPath(line3);
            }
            pathTransition3.setNode(imageView);
            pathTransition3.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition3.setCycleCount(1);
            pathTransition3.setAutoReverse(false);
            pathTransition3.play();
        });

        pathTransition3.setOnFinished(e -> {
            pathTransition4.setDuration(Duration.seconds(0.6));
            if (!(line3.getEndX() == line4.getStartX() && line3.getEndY() == line4.getStartY())) {
                Line newLine1 = new Line();
                newLine1.setStrokeWidth(5.0);
                newLine1.setStroke(javafx.scene.paint.Color.BLACK);
                newLine1.setEndX(line4.getStartX());
                newLine1.setStartX(line4.getEndX());
                newLine1.setEndY(line4.getStartY());
                newLine1.setStartY(line4.getEndY());
                pathTransition4.setPath(newLine1);
            } else {
                pathTransition4.setPath(line4);
            }

            pathTransition4.setNode(imageView);
            pathTransition4.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition4.setCycleCount(1);
            pathTransition4.setAutoReverse(false);
            pathTransition4.play();
        });

        pathTransition4.setOnFinished(e -> {
            pathTransition5.setDuration(Duration.seconds(0.6));
            if (!(line4.getEndX() == line5.getStartX() && line4.getEndY() == line5.getStartY())) {
                Line newLine1 = new Line();
                newLine1.setStrokeWidth(5.0);
                newLine1.setStroke(javafx.scene.paint.Color.BLACK);
                newLine1.setEndX(line5.getStartX());
                newLine1.setStartX(line5.getEndX());
                newLine1.setEndY(line5.getStartY());
                newLine1.setStartY(line5.getEndY());
                pathTransition5.setPath(newLine1);
            } else {
                pathTransition5.setPath(line5);
            }
            pathTransition5.setNode(imageView);
            pathTransition5.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition5.setCycleCount(1);
            pathTransition5.setAutoReverse(false);
            pathTransition5.play();
        });
        pathTransition5.setOnFinished(e -> {
            line1.setVisible(false);
            line2.setVisible(false);
            line3.setVisible(false);
            line4.setVisible(false);
            line5.setVisible(false);
        });
    }


    public void animation(Line line1, Line line2, Line line3, Line line4, Line line5, Line line6, ImageView imageView) {
        //Creating path transition objects
        PathTransition pathTransition = new PathTransition();
        PathTransition pathTransition2 = new PathTransition();
        PathTransition pathTransition3 = new PathTransition();
        PathTransition pathTransition4 = new PathTransition();
        PathTransition pathTransition5 = new PathTransition();
        PathTransition pathTransition6 = new PathTransition();

        pathTransition.setDuration(Duration.seconds(0.5));
        pathTransition.setPath(line1);
        pathTransition.setNode(imageView);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(1);
        pathTransition.setAutoReverse(false);

        // Start the animation
        pathTransition.play();

        pathTransition.setOnFinished(e -> {
            pathTransition2.setDuration(Duration.seconds(0.5));
            if (!(line1.getEndX() == line2.getStartX() && line1.getEndY() == line2.getStartY())) {
                Line newLine1 = new Line();
                newLine1.setStrokeWidth(5.0);
                newLine1.setStroke(javafx.scene.paint.Color.BLACK);
                newLine1.setEndX(line2.getStartX());
                newLine1.setStartX(line2.getEndX());
                newLine1.setEndY(line2.getStartY());
                newLine1.setStartY(line2.getEndY());
                pathTransition2.setPath(newLine1);
            } else {
                pathTransition2.setPath(line2);
            }

            pathTransition2.setNode(imageView);
            pathTransition2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition2.setCycleCount(1);
            pathTransition2.setAutoReverse(false);
            pathTransition2.play();
        });

        pathTransition2.setOnFinished(e -> {
            pathTransition3.setDuration(Duration.seconds(0.5));
            if (!(line2.getEndX() == line3.getStartX() && line2.getEndY() == line3.getStartY())) {
                Line newLine1 = new Line();
                newLine1.setStrokeWidth(5.0);
                newLine1.setStroke(javafx.scene.paint.Color.BLACK);
                newLine1.setEndX(line3.getStartX());
                newLine1.setStartX(line3.getEndX());
                newLine1.setEndY(line3.getStartY());
                newLine1.setStartY(line3.getEndY());
                pathTransition3.setPath(newLine1);
            } else {
                pathTransition3.setPath(line3);
            }
            pathTransition3.setNode(imageView);
            pathTransition3.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition3.setCycleCount(1);
            pathTransition3.setAutoReverse(false);
            pathTransition3.play();
        });

        pathTransition3.setOnFinished(e -> {
            pathTransition4.setDuration(Duration.seconds(0.5));
            if (!(line3.getEndX() == line4.getStartX() && line3.getEndY() == line4.getStartY())) {
                Line newLine1 = new Line();
                newLine1.setStrokeWidth(5.0);
                newLine1.setStroke(javafx.scene.paint.Color.BLACK);
                newLine1.setEndX(line4.getStartX());
                newLine1.setStartX(line4.getEndX());
                newLine1.setEndY(line4.getStartY());
                newLine1.setStartY(line4.getEndY());
                pathTransition4.setPath(newLine1);
            } else {
                pathTransition4.setPath(line4);
            }
            pathTransition4.setNode(imageView);
            pathTransition4.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition4.setCycleCount(1);
            pathTransition4.setAutoReverse(false);
            pathTransition4.play();
        });

        pathTransition4.setOnFinished(e -> {

            pathTransition5.setDuration(Duration.seconds(0.5));
            if (!(line4.getEndX() == line5.getStartX() && line4.getEndY() == line5.getStartY())) {
                Line newLine1 = new Line();
                newLine1.setStrokeWidth(5.0);
                newLine1.setStroke(javafx.scene.paint.Color.BLACK);
                newLine1.setEndX(line5.getStartX());
                newLine1.setStartX(line5.getEndX());
                newLine1.setEndY(line5.getStartY());
                newLine1.setStartY(line5.getEndY());
                pathTransition5.setPath(newLine1);
            } else {
                pathTransition5.setPath(line5);
            }
            pathTransition5.setNode(imageView);
            pathTransition5.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition5.setCycleCount(1);
            pathTransition5.setAutoReverse(false);
            pathTransition5.play();
        });

        pathTransition5.setOnFinished(e -> {

            pathTransition6.setDuration(Duration.seconds(0.5));
            if (!(line5.getEndX() == line6.getStartX() && line5.getEndY() == line6.getStartY())) {
                Line newLine1 = new Line();
                newLine1.setStrokeWidth(5.0);
                newLine1.setStroke(javafx.scene.paint.Color.BLACK);
                newLine1.setEndX(line6.getStartX());
                newLine1.setStartX(line6.getEndX());
                newLine1.setEndY(line6.getStartY());
                newLine1.setStartY(line6.getEndY());
                pathTransition6.setPath(newLine1);
            } else {
                pathTransition6.setPath(line6);
            }

            pathTransition6.setNode(imageView);
            pathTransition6.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition6.setCycleCount(1);
            pathTransition6.setAutoReverse(false);
            pathTransition6.play();
        });
        pathTransition6.setOnFinished(e -> {
            line1.setVisible(false);
            line2.setVisible(false);
            line3.setVisible(false);
            line4.setVisible(false);
            line5.setVisible(false);
            line6.setVisible(false);
        });
    }

    public int calculatingDistance(int x1, int y1, int x2, int y2) {
        double distance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
        distance = Math.ceil(distance);
        return (int) distance;
    }

    public int calculatingDistance(int cellID1, int cellID2) {
        int x1, y1, x2, y2;
        if (cellID1 % 10 != 0) {
            x1 = cellID1 % 10;
            y1 = cellID1 / 10 + 1;
        } else {
            x1 = 10;
            y1 = cellID1 / 10;
        }
        if (cellID2 % 10 != 0) {
            x2 = cellID2 % 10;
            y2 = cellID2 / 10 + 1;
        } else {
            x2 = 10;
            y2 = cellID2 / 10;
        }
        return calculatingDistance(x1, y1, x2, y2);
    }

    public String returnCityName(Object[] levelMap, int cellID) {
        for (int i = 0; i < levelMap.length; i++) {
            if (levelMap[i] instanceof City && i == cellID) {
                return ((City) levelMap[i]).getName();
            }
        }
        return "null";
    }

    public String returnCityNameWithCityID(Object[] levelMap, int cityID) {
        for (int i = 0; i < levelMap.length; i++) {
            if (levelMap[i] instanceof City) {
                if (((City) levelMap[i]).getCityID() == cityID)
                    return ((City) levelMap[i]).getName();
            }
        }
        return "null";
    }

    public int returnCityID(Object[] levelMap, String cityName) {
        for (int i = 0; i < levelMap.length; i++) {
            if (levelMap[i] instanceof City)
                if (((City) levelMap[i]).getName().equals(cityName)) {
                    return ((City) levelMap[i]).getCityID();
                }
        }
        return -1;
    }

    public int returnCityID(Object[] levelMap, int cityCell) {
        if (levelMap[cityCell] instanceof City) {
            return ((City) levelMap[cityCell]).getCityID();
        }
        return -1;
    }

    public int returnCityCell(Object[] levelMap, String cityName) {
        for (int i = 0; i < levelMap.length; i++) {
            if (levelMap[i] instanceof City)
                if (((City) levelMap[i]).getName().equals(cityName)) {
                    return i;
                }
        }
        return -1;
    }

    public int returnCityCell(Object[] levelMap, int cityID) {
        for (int i = 0; i < levelMap.length; i++) {
            if (levelMap[i] instanceof City)
                if (((City) levelMap[i]).getCityID() == cityID) {
                    return i;
                }
        }
        return -1;
    }

    public City returnCity(Level level, int cityID) {
        for (int i = 0; i < level.levelMap.length; i++) {
            if (level.levelMap[i] instanceof City)
                if (((City) level.levelMap[i]).getCityID() == cityID) {
                    return ((City) level.levelMap[i]).clone();
                }
        }
        return null;
    }

    public City returnCity(Level level, String cityName) {
        for (int i = 0; i < level.levelMap.length; i++) {
            if (level.levelMap[i] instanceof City)
                if (((City) level.levelMap[i]).getName().equals(cityName)) {
                    return ((City) level.levelMap[i]).clone();
                }
        }
        return new City();
    }


    public String currentCityInformation(Level level, String cityName) {
        City city = returnCity(level, cityName);
        String text = "Current City: " + cityName;
        for (int i = 0; i < city.getPassengerList().size(); i++) {
            text = text + "\n" + city.getPassengerList().get(i).getNumberOfPassengers() + " passenger wants to go " + returnCityNameWithCityID(level.levelMap, city.getPassengerList().get(i).getDestinationCityID());
        }
        return text;
    }

    public String currentCityInformation(Level level, int cityID) {
        City city = returnCity(level, cityID);
        String text = "Current City: " + returnCityName(level.levelMap, cityID);
        for (int i = 0; i < city.getPassengerList().size(); i++) {
            text = text + "\n" + city.getPassengerList().get(i).getNumberOfPassengers() + " passenger wants to go " + returnCityNameWithCityID(level.levelMap, city.getPassengerList().get(i).getDestinationCityID());
        }
//        for (Passenger passenger : city.getPassengerList()) {
//            text=text + "\n" + passenger.getNumberOfPassengers() + " passenger wants to go " + returnCityName(level.levelMap,passenger.getDestinationCityID());
//        }
        return text;
    }

    public ArrayList<Line> drawLines(Object[] levelMap, int startCellId, int endCellId) {
        boolean isContinue = true;
        int vertHori = 0;
        ArrayList<Integer> lasts = new ArrayList<>();
        ArrayList<Line> lineList = new ArrayList<>();
        int p = endCellId, startX = 0, startY = 0,k=startCellId;
        startX = ((p - 1) % 10) * 60;
        startY = ((p - 1) / 10) * 60;
        while (isContinue) {
            Line line = new Line();
            line.setStrokeWidth(5.0);
            line.setStroke(Color.BLACK);
            line.setVisible(true);

            if ((p - 1) % 10 == (startCellId - 1) % 10 && (p - 1) / 10 == (startCellId - 1) / 10) {
                line.setStartX(startX);
                line.setEndX(((k - 1) % 10) * 60);
                line.setStartY(startY);
                line.setEndY(((k - 1) / 10) * 60);

                line.setStartX(line.getStartX()+30);
                line.setStartY(line.getStartY()+30);
                line.setEndX(line.getEndX()+30);
                line.setEndY(line.getEndY()+30);

                lineList.add(line);
                isContinue = false;
            }
            else if((p - 10) % 10 == (startCellId - 10) % 10 && (p - 10) / 10 == (startCellId - 10) / 10){
                line.setStartX(startX);
                line.setEndX(((k - 10) % 10) * 60);
                line.setStartY(startY);
                line.setEndY(((k - 10) / 10) * 60);

                line.setStartX(line.getStartX()+30);
                line.setStartY(line.getStartY()+30);
                line.setEndX(line.getEndX()+30);
                line.setEndY(line.getEndY()+30);

                lineList.add(line);
                isContinue = false;
            }
            else if ((p + 1) % 10 == (startCellId + 1) % 10 && (p + 1) / 10 == (startCellId + 1) / 10) {
                line.setStartX(startX);
                line.setEndX(((k + 1) % 10) * 60);
                line.setStartY(startY);
                line.setEndY(((k + 1) / 10) * 60);

                line.setStartX(line.getStartX()+30);
                line.setStartY(line.getStartY()+30);
                line.setEndX(line.getEndX()+30);
                line.setEndY(line.getEndY()+30);

                lineList.add(line);
                isContinue = false;
            }
            else if((p + 10) % 10 == (startCellId + 10) % 10 && (p + 10) / 10 == (startCellId + 10) / 10){
                line.setStartX(startX);
                line.setEndX(((k + 10) % 10) * 60);
                line.setStartY(startY);
                line.setEndY(((k + 10) / 10) * 60);

                line.setStartX(line.getStartX()+30);
                line.setStartY(line.getStartY()+30);
                line.setEndX(line.getEndX()+30);
                line.setEndY(line.getEndY()+30);

                lineList.add(line);
                isContinue = false;
            }
            if ((p - 1) % 10 > (startCellId - 1) % 10 && (p - 1) / 10 > (startCellId - 1) / 10) {//eğer sağ aşağıda ise
                if (p == startCellId) {
                    isContinue = false;
                }
                if ((levelMap[p - 1] == null && (p - 1) % 10 != 0 && !lasts.contains(p - 1)) || (p-1 == startCellId && levelMap[p-1] instanceof City) ) {
                    if (vertHori != 1 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 1;
                    lasts.add(p);
                    p--;
                } else if ((levelMap[p - 10] == null && (p - 1) / 10 != 0 && !lasts.contains(p - 10)) || (p-10 == startCellId && levelMap[p-10] instanceof City) ) {

                    if (vertHori != 2 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 2;
                    lasts.add(p);
                    p = p - 10;

                } else if ((levelMap[p + 1] == null && (p - 1) % 10 != 9 && !lasts.contains(p + 1)) || (p+1 == startCellId && levelMap[p+1] instanceof City) ) {

                    if (vertHori != 3 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 3;
                    lasts.add(p);
                    p++;
                } else if ((levelMap[p + 10] == null && (p - 1) / 10 != 9 && !lasts.contains(p + 10)) || (p+10 == startCellId && levelMap[p+10] instanceof City) ) {

                    if (vertHori != 4 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 4;
                    lasts.add(p);
                    p = p + 10;
                }
                else{
                    return drawLines(levelMap,endCellId,startCellId);
                }
            } else if ((p - 1) % 10 > (startCellId - 1) % 10 && (p - 1) / 10 < (startCellId - 1) / 10) {//eğer sağ yukarda ise
                if (p == startCellId) {
                    isContinue = false;
                }
                if ((levelMap[p - 1] == null && (p - 1) % 10 != 0 && !lasts.contains(p - 1)) || (p-1 == startCellId && levelMap[p-1] instanceof City) ) {
                    System.out.println("sağ yukarı");
                    if (vertHori != 1 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 1;
                    lasts.add(p);
                    p--;
                } else if ((levelMap[p + 10] == null && (p - 1) / 10 != 9 && !lasts.contains(p + 10)) || (p+10 == startCellId && levelMap[p+10] instanceof City) ) {

                    if (vertHori != 4 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 4;
                    lasts.add(p);
                    p = p + 10;
                } else if ((levelMap[p + 1] == null && (p - 1) % 10 != 9 && !lasts.contains(p + 1)) || (p+1 == startCellId && levelMap[p+1] instanceof City)) {

                    if (vertHori != 3 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 3;
                    lasts.add(p);
                    p++;
                } else if ((levelMap[p - 10] == null && (p - 1) / 10 != 0 && !lasts.contains(p - 10)) || (p-10 == startCellId && levelMap[p-10] instanceof City) ) {

                    if (vertHori != 2 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 2;
                    lasts.add(p);
                    p = p - 10;

                }
                else{
                    return drawLines(levelMap,endCellId,startCellId);
                }
            } else if ((p - 1) % 10 < (startCellId - 1) % 10 && (p - 1) / 10 > (startCellId - 1) / 10) {//eğer sol aşağıda ise
                if (p == startCellId) {
                    isContinue = false;
                }
                if ((levelMap[p + 1] == null && (p - 1) % 10 != 9 && !lasts.contains(p + 1)) || (p+1 == startCellId && levelMap[p+1] instanceof City) ) {

                    if (vertHori != 3 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 3;
                    lasts.add(p);
                    p++;
                } else if ((levelMap[p - 10] == null && (p - 1) / 10 != 0 && !lasts.contains(p - 10)) || (p-10 == startCellId && levelMap[p-10] instanceof City) ) {

                    if (vertHori != 2 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 2;
                    lasts.add(p);
                    p = p - 10;

                } else if ((levelMap[p - 1] == null && (p - 1) % 10 != 0 && !lasts.contains(p - 1)) || (p-1 == startCellId && levelMap[p-1] instanceof City) ) {
                    System.out.println("sol aşağı");
                    if (vertHori != 1 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 1;
                    lasts.add(p);
                    p--;
                } else if ((levelMap[p + 10] == null && (p - 1) / 10 != 9 && !lasts.contains(p + 10)) || (p+10 == startCellId && levelMap[p+10] instanceof City) ) {

                    if (vertHori != 4 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 4;
                    lasts.add(p);
                    p = p + 10;
                }
                else{
                    return drawLines(levelMap,endCellId,startCellId);
                }
            } else if ((p - 1) % 10 < (startCellId - 1) % 10 && (p - 1) / 10 < (startCellId - 1) / 10) {//eğer sol yukarda ise
                if (p == startCellId) {
                    isContinue = false;
                }
                if ((levelMap[p + 1] == null && (p - 1) % 10 != 9 && !lasts.contains(p + 1)) || (p+1 == startCellId && levelMap[p+1] instanceof City) ) {

                    if (vertHori != 3 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 3;
                    lasts.add(p);
                    p++;
                } else if ((levelMap[p + 10] == null && (p - 1) / 10 != 9 && !lasts.contains(p + 10)) || (p+10 == startCellId && levelMap[p+10] instanceof City) ) {

                    if (vertHori != 4 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 4;
                    lasts.add(p);
                    p = p + 10;
                } else if ((levelMap[p - 1] == null && (p - 1) % 10 != 0 && !lasts.contains(p - 1)) || (p-1 == startCellId && levelMap[p-1] instanceof City) ) {
                    System.out.println("sol yukarı");
                    if (vertHori != 1 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 1;
                    lasts.add(p);
                    p--;
                } else if ((levelMap[p - 10] == null && (p - 1) / 10 != 0 && !lasts.contains(p - 10)) || (p-10 == startCellId && levelMap[p-10] instanceof City) ) {

                    if (vertHori != 2 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 2;
                    lasts.add(p);
                    p = p - 10;

                }
                else{
                    return drawLines(levelMap,endCellId,startCellId);
                }
            }


            //EŞİTLİKLER
            else if ((p - 1) % 10 > (startCellId - 1) % 10 && (p - 1) / 10 == (startCellId - 1) / 10) {//eğer sağında ise
                if (p == startCellId) {
                    isContinue = false;
                }
                if ((levelMap[p - 1] == null && (p - 1) % 10 != 0 && !lasts.contains(p - 1)) || (p-1 == startCellId && levelMap[p-1] instanceof City) ) {
                    System.out.println("sağa");
                    if (vertHori != 1 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 1;
                    lasts.add(p);
                    p--;
                } else if ((levelMap[p - 10] == null && (p - 1) / 10 != 0 && !lasts.contains(p - 10)) || (p-10 == startCellId && levelMap[p-10] instanceof City) ) {

                    if (vertHori != 2 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 2;
                    lasts.add(p);
                    p = p - 10;

                } else if ((levelMap[p + 10] == null && (p - 1) / 10 != 9 && !lasts.contains(p + 10)) || (p+10 == startCellId && levelMap[p+10] instanceof City) ) {

                    if (vertHori != 4 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 4;
                    lasts.add(p);
                    p = p + 10;
                } else if ((levelMap[p + 1] == null && (p - 1) % 10 != 9 && !lasts.contains(p + 1)) || (p+1 == startCellId && levelMap[p+1] instanceof City) ) {

                    if (vertHori != 3 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 3;
                    lasts.add(p);
                    p++;
                }
                else{
                    return drawLines(levelMap,endCellId,startCellId);
                }
            } else if ((p - 1) % 10 < (startCellId - 1) % 10 && (p - 1) / 10 == (startCellId - 1) / 10) {//eğer solunda ise
                if (p == startCellId) {
                    isContinue = false;
                }
                if ((levelMap[p + 1] == null && (p - 1) % 10 != 9 && !lasts.contains(p + 1)) || (p+1 == startCellId && levelMap[p+1] instanceof City) ) {

                    if (vertHori != 3 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 3;
                    lasts.add(p);
                    p++;
                } else if ((levelMap[p + 10] == null && (p - 1) / 10 != 9 && !lasts.contains(p + 1)) || (p+10 == startCellId && levelMap[p+10] instanceof City) ) {

                    if (vertHori != 4 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 4;
                    lasts.add(p);
                    p = p + 10;
                } else if ((levelMap[p - 10] == null && (p - 1) / 10 != 0 && !lasts.contains(p - 10)) || (p-10 == startCellId && levelMap[p-10] instanceof City) ) {

                    if (vertHori != 2 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 2;
                    lasts.add(p);
                    p = p - 10;

                } else if ((levelMap[p - 1] == null && (p - 1) % 10 != 0 && !lasts.contains(p - 1)) || (p-1 == startCellId && levelMap[p-1] instanceof City) ) {
                    System.out.println("sola");
                    if (vertHori != 1 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 1;
                    lasts.add(p);
                    p--;
                }
                else{
                    return drawLines(levelMap,endCellId,startCellId);
                }
            } else if ((p - 1) % 10 == (startCellId - 1) % 10 && (p - 1) / 10 < (startCellId - 1) / 10) {//eğer yukarda ise
                if (p == startCellId) {
                    isContinue = false;
                }
                if ( (levelMap[p + 10] == null && (p - 1) / 10 != 9 && !lasts.contains(p + 10)) || (p+10 == startCellId && levelMap[p+10] instanceof City) ) {
                    System.out.println("aşağı");
                    if (vertHori != 4 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 4;
                    lasts.add(p);
                    p = p + 10;
                } else if ((levelMap[p + 1] == null && (p - 1) % 10 != 9 && !lasts.contains(p + 1)) || (p+1 == startCellId && levelMap[p+1] instanceof City) ) {

                    if (vertHori != 3 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 3;
                    lasts.add(p);
                    p++;
                } else if ((levelMap[p - 1] == null && (p - 1) % 10 != 0 && !lasts.contains(p - 1)) || (p-1 == startCellId && levelMap[p-1] instanceof City) ) {
                    System.out.println("sola");
                    if (vertHori != 1 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 1;
                    lasts.add(p);
                    p--;
                } else if ((levelMap[p - 10] == null && (p - 1) / 10 != 0 && !lasts.contains(p - 10)) || (p-10 == startCellId && levelMap[p-10] instanceof City) ) {

                    if (vertHori != 2 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 2;
                    lasts.add(p);
                    p = p - 10;

                }
                else{
                    return drawLines(levelMap,endCellId,startCellId);
                }
            } else if ((p - 1) % 10 == (startCellId - 1) % 10 && (p - 1) / 10 > (startCellId - 1) / 10) {//eğer aşağıda ise

                if (p == startCellId) {
                    isContinue = false;
                }
                if (((levelMap[p - 10] == null && (p - 1) / 10 != 0)  && !lasts.contains(p - 10)) || (p-10 == startCellId && levelMap[p-10] instanceof City) ) {
                    if (vertHori != 2 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 2;
                    lasts.add(p);
                    p = p - 10;

                } else if ((levelMap[p - 1] == null && (p - 1) % 10 != 0&& !lasts.contains(p - 1)) || (p-1 == startCellId && levelMap[p-1] instanceof City) ) {
                    System.out.println("asağı");
                    if (vertHori != 1 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 1;
                    lasts.add(p);
                    p--;
                }else if ((levelMap[p + 1] == null && (p - 1) % 10 != 9 && !lasts.contains(p + 1)) || (p+1 == startCellId && levelMap[p+1] instanceof City) ) {

                    if (vertHori != 3 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 3;
                    lasts.add(p);
                    p++;
                } else if ((levelMap[p + 10] == null && (p - 1) / 10 != 9 && !lasts.contains(p + 10)) || (p+10 == startCellId && levelMap[p+10] instanceof City)) {

                    if (vertHori != 4 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX()+30);
                        line.setStartY(line.getStartY()+30);
                        line.setEndX(line.getEndX()+30);
                        line.setEndY(line.getEndY()+30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    System.out.println("p = " + p);
                    vertHori = 4;
                    lasts.add(p);
                    p = p + 10;
                }
                else{
                    return drawLines(levelMap,endCellId,startCellId);
                }
            }
        }
        return lineList;
    }

}
