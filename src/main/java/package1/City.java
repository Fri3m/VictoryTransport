//150121046 Ömer Can Şimşek
//150121044 Ömer Yıldırım
//150121038 Batuhan Kurt

//This class is a city class. It has name, cellID, cityID and passengerList attributes.

package package1;
import java.util.ArrayList;

public class City implements Cloneable{

    String name = null;
    int cellID = 0;
    int cityID = 0;
    ArrayList<Passenger> passengerList = new ArrayList<Passenger>();


    public City(String name) {
        this.name=name;
    }
    public City() {

    }
    void cityArrangment(int cellID, int cityID){
        this.cellID=cellID;
        this.cityID = cityID;
    }
    public City clone() {
        City city = new City();
        city.name = this.name;
        city.cellID = this.cellID;
        city.cityID = this.cityID;
        city.passengerList = this.passengerList;
        return city;
    }



    public String getName() {
        return name;
    }
    public int getCellID() {
        return cellID;
    }
    public int getCityID() {
        return cityID;
    }
    public ArrayList<Passenger> getPassengerList() {
        return passengerList;
    }



}