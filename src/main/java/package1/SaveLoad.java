package package1;

import com.example.victorytransport.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SaveLoad {
    public void saveWhichLevel(String levelName) {
        File saveFile = new File("whichLevel.txt");
        try {
            FileWriter fileWriter = new FileWriter(saveFile);
            fileWriter.write(levelName);
            fileWriter.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void saveActions(Text actionsText) {
        File saveFile = new File("ActionText.txt");
        try {
            FileWriter fileWriter = new FileWriter(saveFile);
            fileWriter.write(actionsText.getText());
            fileWriter.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void save(Level level) {
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
    }


    public Level load(){
        File saveFile = new File("saveFile.txt");
        Level level = new Level(saveFile);
        return level;
    }
    public Text loadActions() {
        Scanner actionReader = new Scanner("ActionText.txt");
        Text actionsText = new Text();
        if (actionReader.hasNextLine()) {
            actionsText.setText(actionsText.getText() +"\n"+ actionReader.nextLine());
        }
        return actionsText;
    }
}

