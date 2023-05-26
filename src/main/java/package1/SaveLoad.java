package package1;

import com.example.victorytransport.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveLoad {
    public void save(Level level,double score) {
        File saveFile = new File("saveFile.txt");
        try {
            FileWriter fileWriter = new FileWriter(saveFile);
            for(Object obj : level.levelObjects) {
                if (obj instanceof City){
                    fileWriter.write("City,"+((City) obj).getName()+","+((City) obj).getCellID()+","+((City) obj).getCityID()+"\n");
                }
            }
            for(Object obj : level.levelObjects) {
                if (obj instanceof Passenger){
                    fileWriter.write("Passenger,"+((Passenger) obj).numberOfPassengers+","+((Passenger) obj).getStartingCityID()+","+((Passenger) obj).getDestinationCityID()+"\n");
                }
            }
            PublicMethods publicMethods = new PublicMethods();

            for(Object obj : level.levelObjects) {
                if (obj instanceof Vehicle){
                    fileWriter.write("Vehicle,"+publicMethods.returnCityID(level.levelMap, ((Vehicle) obj).getCellID())+","+((Vehicle) obj).getPassengerCapacity()+"\n");
                }
            }
            for(Object obj : level.levelObjects) {
                if (obj instanceof Fixed){
                    fileWriter.write("Fixed,"+((Fixed) obj).getCellID()+"\n");
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File scoreFile = new File("scoreFile.txt");
        try {
            FileWriter fileWriter = new FileWriter(scoreFile);
            fileWriter.write(String.valueOf(score));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    //Loading level
    public Level load(){
        File saveFile = new File("saveFile.txt");
        Level level = new Level(saveFile);
        return level;
    }
    public double loadScore(){
        File scoreFile = new File("scoreFile.txt");
        double score = 0;
        try {
            score = Double.parseDouble(String.valueOf(scoreFile));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return score;
    }

}

