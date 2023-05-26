package package1;

import com.example.victorytransport.PublicMethods;

public class Passenger implements Cloneable {

    int numberOfPassengers;
    int startingCityID;
    int destinationCityID;
    int distance;

    public Passenger(int numberOfPassengers, int startingCityID, int destinationCityID) {

        this.numberOfPassengers = numberOfPassengers;
        this.startingCityID = startingCityID;
        this.destinationCityID = destinationCityID;

    }
    public Passenger clone(){
        Passenger passenger = new Passenger(numberOfPassengers,startingCityID,destinationCityID);
        passenger.setDistance(getDistance());
        return passenger;
    }
    public Passenger clone(int distance){
        Passenger passenger = new Passenger(numberOfPassengers,startingCityID,destinationCityID);
        passenger.setDistance(distance);
        return passenger;
    }
    //Getter and setters
    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }
    public int getStartingCityID() {
        return startingCityID;
    }
    public int getDestinationCityID() {
        return destinationCityID;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}