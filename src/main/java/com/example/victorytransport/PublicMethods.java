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

    public PublicMethods() {

    }

    //Used for fixed cells.
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

    //Used for fixed cells.
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

    //Used for city images.
    public Circle createCircleImageView(String cityName, Object[] levelMap) {
        Circle circle = new Circle();
        Image image = new Image(new File("images/" + cityName + ".png").toURI().toString());
        circle.setFill(new ImagePattern(image));
        circle.setRadius(30);
        circle.setVisible(true);
        City city = (City) levelMap[returnCityCell(levelMap, cityName)];
        circle.setCenterX((city.getCellID() - 1) % 10 * 60 + 30);
        circle.setCenterY((city.getCellID() - 1) / 10 * 60 + 30);
        return circle;
    }

    //Used for car animation.
    public ArrayList<PathTransition> createPathTransitions(ArrayList<Line> lines, ImageView car, int startCellID) {
        Duration duration1 = Duration.seconds(1);
        ArrayList<PathTransition> pathTransitions = new ArrayList<>();
        if (lines.isEmpty()) {
            return new ArrayList<>();
        } else {
            double x = 3.0 / lines.size();
            duration1 = Duration.seconds(x);
        }
        int startCityX = (startCellID - 1) % 10 * 60 + 30;
        int startCityY = (startCellID - 1) / 10 * 60 + 30;

        if (lines.get(lines.size() - 1).getEndX() == startCityX && lines.get(lines.size() - 1).getEndY() == startCityY) {
            for (int i = 0; i < lines.size(); i++) {
                PathTransition pathTransition = new PathTransition();
                pathTransition.setDuration(duration1);
                pathTransition.setPath(lines.get(i));
                pathTransition.setNode(car);
                pathTransitions.add(pathTransition);
            }
        } else {
            for (int i = lines.size() - 1; i >= 0; i--) {
                Line newLine = new Line();
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

    //Used for calculating the distance between two cities.
    public int calculatingDistance(int x1, int y1, int x2, int y2) {
        double distance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
        distance = Math.ceil(distance);
        return (int) distance;
    }

    //Used for calculating the distance between two cities.
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

    //Used for returning the cellName of a city.
    public String returnCityName(Object[] levelMap, int cellID) {
        for (int i = 0; i < levelMap.length; i++) {
            if (levelMap[i] instanceof City && i == cellID) {
                return ((City) levelMap[i]).getName();
            }
        }
        return "null";
    }
    //Used for returning the cellName of a city with cityID.
    public String returnCityNameWithCityID(Object[] levelMap, int cityID) {
        for (int i = 0; i < levelMap.length; i++) {
            if (levelMap[i] instanceof City) {
                if (((City) levelMap[i]).getCityID() == cityID)
                    return ((City) levelMap[i]).getName();
            }
        }
        return "null";
    }
    //Used for returning the cityId.
    public int returnCityID(Object[] levelMap, int cityCell) {
        if (levelMap[cityCell] instanceof City) {
            return ((City) levelMap[cityCell]).getCityID();
        }
        return -1;
    }
    //Used for returning the cityCell with cityName.
    public int returnCityCell(Object[] levelMap, String cityName) {
        for (int i = 0; i < levelMap.length; i++) {
            if (levelMap[i] instanceof City)
                if (((City) levelMap[i]).getName().equals(cityName)) {
                    return i;
                }
        }
        return -1;
    }
    //Used for returning the citycell with cityID.
    public int returnCityCell(Object[] levelMap, int cityID) {
        for (int i = 0; i < levelMap.length; i++) {
            if (levelMap[i] instanceof City)
                if (((City) levelMap[i]).getCityID() == cityID) {
                    return i;
                }
        }
        return -1;
    }

    //Used for returning the city.
    public City returnCity(Level level, String cityName) {
        for (int i = 0; i < level.levelMap.length; i++) {
            if (level.levelMap[i] instanceof City)
                if (((City) level.levelMap[i]).getName().equals(cityName)) {
                    return ((City) level.levelMap[i]).clone();
                }
        }
        return new City();
    }

    //Used for giving city information.
    public String currentCityInformation(Level level, String cityName) {
        City city = returnCity(level, cityName);
        String text = "Current City: " + cityName;
        for (int i = 0; i < city.getPassengerList().size(); i++) {
            text = text + "\n" + city.getPassengerList().get(i).getNumberOfPassengers() + " passenger wants to go " + returnCityNameWithCityID(level.levelMap, city.getPassengerList().get(i).getDestinationCityID());
        }
        return text;
    }

    //Used for drawing paths.
    public ArrayList<Line> drawLines(Object[] levelMap, int startCellId, int endCellId) {
        boolean isContinue = true;
        int vertHori = 0;
        ArrayList<Integer> lasts = new ArrayList<>();
        ArrayList<Line> lineList = new ArrayList<>();
        int p = endCellId, startX = 0, startY = 0, k = startCellId;
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

                line.setStartX(line.getStartX() + 30);
                line.setStartY(line.getStartY() + 30);
                line.setEndX(line.getEndX() + 30);
                line.setEndY(line.getEndY() + 30);

                lineList.add(line);
                isContinue = false;
            } else if ((p - 10) % 10 == (startCellId - 10) % 10 && (p - 10) / 10 == (startCellId - 10) / 10) {
                line.setStartX(startX);
                line.setEndX(((k - 10) % 10) * 60);
                line.setStartY(startY);
                line.setEndY(((k - 10) / 10) * 60);

                line.setStartX(line.getStartX() + 30);
                line.setStartY(line.getStartY() + 30);
                line.setEndX(line.getEndX() + 30);
                line.setEndY(line.getEndY() + 30);

                lineList.add(line);
                isContinue = false;
            } else if ((p + 1) % 10 == (startCellId + 1) % 10 && (p + 1) / 10 == (startCellId + 1) / 10) {
                line.setStartX(startX);
                line.setEndX(((k + 1) % 10) * 60);
                line.setStartY(startY);
                line.setEndY(((k + 1) / 10) * 60);

                line.setStartX(line.getStartX() + 30);
                line.setStartY(line.getStartY() + 30);
                line.setEndX(line.getEndX() + 30);
                line.setEndY(line.getEndY() + 30);

                lineList.add(line);
                isContinue = false;
            } else if ((p + 10) % 10 == (startCellId + 10) % 10 && (p + 10) / 10 == (startCellId + 10) / 10) {
                line.setStartX(startX);
                line.setEndX(((k + 10) % 10) * 60);
                line.setStartY(startY);
                line.setEndY(((k + 10) / 10) * 60);

                line.setStartX(line.getStartX() + 30);
                line.setStartY(line.getStartY() + 30);
                line.setEndX(line.getEndX() + 30);
                line.setEndY(line.getEndY() + 30);

                lineList.add(line);
                isContinue = false;
            }
            if ((p - 1) % 10 > (startCellId - 1) % 10 && (p - 1) / 10 > (startCellId - 1) / 10) {//eğer sağ aşağıda ise
                if (p == startCellId) {
                    isContinue = false;
                }
                if ((levelMap[p - 1] == null && (p - 1) % 10 != 0 && !lasts.contains(p - 1)) || (p - 1 == startCellId && levelMap[p - 1] instanceof City)) {
                    if (vertHori != 1 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    vertHori = 1;
                    lasts.add(p);
                    p--;
                } else if ((levelMap[p - 10] == null && (p - 1) / 10 != 0 && !lasts.contains(p - 10)) || (p - 10 == startCellId && levelMap[p - 10] instanceof City)) {

                    if (vertHori != 2 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    vertHori = 2;
                    lasts.add(p);
                    p = p - 10;

                } else if ((levelMap[p + 1] == null && (p - 1) % 10 != 9 && !lasts.contains(p + 1)) || (p + 1 == startCellId && levelMap[p + 1] instanceof City)) {

                    if (vertHori != 3 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    vertHori = 3;
                    lasts.add(p);
                    p++;
                } else if ((levelMap[p + 10] == null && (p - 1) / 10 != 9 && !lasts.contains(p + 10)) || (p + 10 == startCellId && levelMap[p + 10] instanceof City)) {

                    if (vertHori != 4 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    vertHori = 4;
                    lasts.add(p);
                    p = p + 10;
                } else {
                    return drawLines(levelMap, endCellId, startCellId);
                }
            } else if ((p - 1) % 10 > (startCellId - 1) % 10 && (p - 1) / 10 < (startCellId - 1) / 10) {//eğer sağ yukarda ise
                if (p == startCellId) {
                    isContinue = false;
                }
                if ((levelMap[p - 1] == null && (p - 1) % 10 != 0 && !lasts.contains(p - 1)) || (p - 1 == startCellId && levelMap[p - 1] instanceof City)) {
                    if (vertHori != 1 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    vertHori = 1;
                    lasts.add(p);
                    p--;
                } else if ((levelMap[p + 10] == null && (p - 1) / 10 != 9 && !lasts.contains(p + 10)) || (p + 10 == startCellId && levelMap[p + 10] instanceof City)) {

                    if (vertHori != 4 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    vertHori = 4;
                    lasts.add(p);
                    p = p + 10;
                } else if ((levelMap[p + 1] == null && (p - 1) % 10 != 9 && !lasts.contains(p + 1)) || (p + 1 == startCellId && levelMap[p + 1] instanceof City)) {

                    if (vertHori != 3 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    vertHori = 3;
                    lasts.add(p);
                    p++;
                } else if ((levelMap[p - 10] == null && (p - 1) / 10 != 0 && !lasts.contains(p - 10)) || (p - 10 == startCellId && levelMap[p - 10] instanceof City)) {

                    if (vertHori != 2 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    vertHori = 2;
                    lasts.add(p);
                    p = p - 10;

                } else {
                    return drawLines(levelMap, endCellId, startCellId);
                }
            } else if ((p - 1) % 10 < (startCellId - 1) % 10 && (p - 1) / 10 > (startCellId - 1) / 10) {//eğer sol aşağıda ise
                if (p == startCellId) {
                    isContinue = false;
                }
                if ((levelMap[p + 1] == null && (p - 1) % 10 != 9 && !lasts.contains(p + 1)) || (p + 1 == startCellId && levelMap[p + 1] instanceof City)) {

                    if (vertHori != 3 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    vertHori = 3;
                    lasts.add(p);
                    p++;
                } else if ((levelMap[p - 10] == null && (p - 1) / 10 != 0 && !lasts.contains(p - 10)) || (p - 10 == startCellId && levelMap[p - 10] instanceof City)) {

                    if (vertHori != 2 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    vertHori = 2;
                    lasts.add(p);
                    p = p - 10;

                } else if ((levelMap[p - 1] == null && (p - 1) % 10 != 0 && !lasts.contains(p - 1)) || (p - 1 == startCellId && levelMap[p - 1] instanceof City)) {
                    if (vertHori != 1 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    vertHori = 1;
                    lasts.add(p);
                    p--;
                } else if ((levelMap[p + 10] == null && (p - 1) / 10 != 9 && !lasts.contains(p + 10)) || (p + 10 == startCellId && levelMap[p + 10] instanceof City)) {

                    if (vertHori != 4 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    vertHori = 4;
                    lasts.add(p);
                    p = p + 10;
                } else {
                    return drawLines(levelMap, endCellId, startCellId);
                }
            } else if ((p - 1) % 10 < (startCellId - 1) % 10 && (p - 1) / 10 < (startCellId - 1) / 10) {//eğer sol yukarda ise
                if (p == startCellId) {
                    isContinue = false;
                }
                if ((levelMap[p + 1] == null && (p - 1) % 10 != 9 && !lasts.contains(p + 1)) || (p + 1 == startCellId && levelMap[p + 1] instanceof City)) {

                    if (vertHori != 3 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    vertHori = 3;
                    lasts.add(p);
                    p++;
                } else if ((levelMap[p + 10] == null && (p - 1) / 10 != 9 && !lasts.contains(p + 10)) || (p + 10 == startCellId && levelMap[p + 10] instanceof City)) {

                    if (vertHori != 4 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    vertHori = 4;
                    lasts.add(p);
                    p = p + 10;
                } else if ((levelMap[p - 1] == null && (p - 1) % 10 != 0 && !lasts.contains(p - 1)) || (p - 1 == startCellId && levelMap[p - 1] instanceof City)) {

                    if (vertHori != 1 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    vertHori = 1;
                    lasts.add(p);
                    p--;
                } else if ((levelMap[p - 10] == null && (p - 1) / 10 != 0 && !lasts.contains(p - 10)) || (p - 10 == startCellId && levelMap[p - 10] instanceof City)) {

                    if (vertHori != 2 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    vertHori = 2;
                    lasts.add(p);
                    p = p - 10;

                } else {
                    return drawLines(levelMap, endCellId, startCellId);
                }
            }


            //EŞİTLİKLER
            else if ((p - 1) % 10 > (startCellId - 1) % 10 && (p - 1) / 10 == (startCellId - 1) / 10) {//eğer sağında ise
                if (p == startCellId) {
                    isContinue = false;
                }
                if ((levelMap[p - 1] == null && (p - 1) % 10 != 0 && !lasts.contains(p - 1)) || (p - 1 == startCellId && levelMap[p - 1] instanceof City)) {

                    if (vertHori != 1 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }

                    vertHori = 1;
                    lasts.add(p);
                    p--;
                } else if ((levelMap[p - 10] == null && (p - 1) / 10 != 0 && !lasts.contains(p - 10)) || (p - 10 == startCellId && levelMap[p - 10] instanceof City)) {

                    if (vertHori != 2 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }

                    vertHori = 2;
                    lasts.add(p);
                    p = p - 10;

                } else if ((levelMap[p + 10] == null && (p - 1) / 10 != 9 && !lasts.contains(p + 10)) || (p + 10 == startCellId && levelMap[p + 10] instanceof City)) {

                    if (vertHori != 4 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    vertHori = 4;
                    lasts.add(p);
                    p = p + 10;
                } else if ((levelMap[p + 1] == null && (p - 1) % 10 != 9 && !lasts.contains(p + 1)) || (p + 1 == startCellId && levelMap[p + 1] instanceof City)) {

                    if (vertHori != 3 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    vertHori = 3;
                    lasts.add(p);
                    p++;
                } else {
                    return drawLines(levelMap, endCellId, startCellId);
                }
            } else if ((p - 1) % 10 < (startCellId - 1) % 10 && (p - 1) / 10 == (startCellId - 1) / 10) {//eğer solunda ise
                if (p == startCellId) {
                    isContinue = false;
                }
                if ((levelMap[p + 1] == null && (p - 1) % 10 != 9 && !lasts.contains(p + 1)) || (p + 1 == startCellId && levelMap[p + 1] instanceof City)) {

                    if (vertHori != 3 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    vertHori = 3;
                    lasts.add(p);
                    p++;
                } else if ((levelMap[p + 10] == null && (p - 1) / 10 != 9 && !lasts.contains(p + 1)) || (p + 10 == startCellId && levelMap[p + 10] instanceof City)) {

                    if (vertHori != 4 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    vertHori = 4;
                    lasts.add(p);
                    p = p + 10;
                } else if ((levelMap[p - 10] == null && (p - 1) / 10 != 0 && !lasts.contains(p - 10)) || (p - 10 == startCellId && levelMap[p - 10] instanceof City)) {

                    if (vertHori != 2 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    vertHori = 2;
                    lasts.add(p);
                    p = p - 10;

                } else if ((levelMap[p - 1] == null && (p - 1) % 10 != 0 && !lasts.contains(p - 1)) || (p - 1 == startCellId && levelMap[p - 1] instanceof City)) {
                    if (vertHori != 1 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }
                    vertHori = 1;
                    lasts.add(p);
                    p--;
                } else {
                    return drawLines(levelMap, endCellId, startCellId);
                }
            } else if ((p - 1) % 10 == (startCellId - 1) % 10 && (p - 1) / 10 < (startCellId - 1) / 10) {//eğer yukarda ise
                if (p == startCellId) {
                    isContinue = false;
                }
                if ((levelMap[p + 10] == null && (p - 1) / 10 != 9 && !lasts.contains(p + 10)) || (p + 10 == startCellId && levelMap[p + 10] instanceof City)) {

                    if (vertHori != 4 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }

                    vertHori = 4;
                    lasts.add(p);
                    p = p + 10;
                } else if ((levelMap[p + 1] == null && (p - 1) % 10 != 9 && !lasts.contains(p + 1)) || (p + 1 == startCellId && levelMap[p + 1] instanceof City)) {

                    if (vertHori != 3 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }

                    vertHori = 3;
                    lasts.add(p);
                    p++;
                } else if ((levelMap[p - 1] == null && (p - 1) % 10 != 0 && !lasts.contains(p - 1)) || (p - 1 == startCellId && levelMap[p - 1] instanceof City)) {

                    if (vertHori != 1 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }

                    vertHori = 1;
                    lasts.add(p);
                    p--;
                } else if ((levelMap[p - 10] == null && (p - 1) / 10 != 0 && !lasts.contains(p - 10)) || (p - 10 == startCellId && levelMap[p - 10] instanceof City)) {

                    if (vertHori != 2 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }

                    vertHori = 2;
                    lasts.add(p);
                    p = p - 10;

                } else {
                    return drawLines(levelMap, endCellId, startCellId);
                }
            } else if ((p - 1) % 10 == (startCellId - 1) % 10 && (p - 1) / 10 > (startCellId - 1) / 10) {//eğer aşağıda ise

                if (p == startCellId) {
                    isContinue = false;
                }
                if (((levelMap[p - 10] == null && (p - 1) / 10 != 0) && !lasts.contains(p - 10)) || (p - 10 == startCellId && levelMap[p - 10] instanceof City)) {
                    if (vertHori != 2 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }

                    vertHori = 2;
                    lasts.add(p);
                    p = p - 10;

                } else if ((levelMap[p - 1] == null && (p - 1) % 10 != 0 && !lasts.contains(p - 1)) || (p - 1 == startCellId && levelMap[p - 1] instanceof City)) {

                    if (vertHori != 1 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }

                    vertHori = 1;
                    lasts.add(p);
                    p--;
                } else if ((levelMap[p + 1] == null && (p - 1) % 10 != 9 && !lasts.contains(p + 1)) || (p + 1 == startCellId && levelMap[p + 1] instanceof City)) {

                    if (vertHori != 3 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }

                    vertHori = 3;
                    lasts.add(p);
                    p++;
                } else if ((levelMap[p + 10] == null && (p - 1) / 10 != 9 && !lasts.contains(p + 10)) || (p + 10 == startCellId && levelMap[p + 10] instanceof City)) {

                    if (vertHori != 4 && vertHori != 0) {
                        line.setStartX(startX);
                        line.setEndX(((p - 1) % 10) * 60);
                        line.setStartY(startY);
                        line.setEndY(((p - 1) / 10) * 60);

                        line.setStartX(line.getStartX() + 30);
                        line.setStartY(line.getStartY() + 30);
                        line.setEndX(line.getEndX() + 30);
                        line.setEndY(line.getEndY() + 30);

                        lineList.add(line);
                        startX = ((p - 1) % 10) * 60;
                        startY = ((p - 1) / 10) * 60;
                    }

                    vertHori = 4;
                    lasts.add(p);
                    p = p + 10;
                } else {
                    return drawLines(levelMap, endCellId, startCellId);
                }
            }
        }
        return lineList;
    }
    public PathTransition createPathTransitionsForMainMenu(Line line, ImageView car) {
        PathTransition pathTransition=new PathTransition();
        pathTransition.setDuration(Duration.seconds(3+(Math.random()*5)));
        pathTransition.setPath(line);
        pathTransition.setNode(car);

        return pathTransition;
    }
}
