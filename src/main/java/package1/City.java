package package1;
import java.util.ArrayList;

public class City implements Cloneable{

    String name = null;
    int cellID = 0;
    int cityID = 0;
    ArrayList<Passenger> passengerList = new ArrayList<Passenger>();
    //Image image;

    public City(String name) {
        this.name=name;
        //this.image=image;
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
//	public Image getImage() {
//		return image;
//	}


}