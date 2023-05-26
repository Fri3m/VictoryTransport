//150121046 Ömer Can Şimşek
//150121044 Ömer Yıldırım
//150121038 Batuhan Kurt

//This class represents vehicles in the game.

package package1;

import com.example.victorytransport.PublicMethods;

import java.util.ArrayList;

public class Vehicle {

    int cellID;
    int passengerCapacity;

    ArrayList<Passenger> passengerList = new ArrayList<>();

    public Vehicle(int cellID, int passengerCapacity) {

        this.cellID = cellID;
        this.passengerCapacity = passengerCapacity;
    }

    public int getTotalPassenger() {
        int totalPassenger = 0;
        for (Passenger passenger : passengerList) {
            totalPassenger += passenger.numberOfPassengers;
        }
        return totalPassenger;
    }

    public int getEmptySpace() {
        return passengerCapacity - getTotalPassenger();
    }

    //Driving vehicle between cities
    public PublicMethods drive(City end) {


        int enterTheVehicle = 0, exitTheVehicle = 0;
        double income = 0;
        Passenger[] passengerArray;
        //Exit the vehicle start
        passengerList.trimToSize();
        passengerArray = new Passenger[passengerList.size()];
        for (int i = 0; i < passengerList.size(); i++) {
            passengerArray[i] = passengerList.get(i);
        }

        for (int i = 0; i < passengerArray.length; i++) {
            if (passengerArray[i].destinationCityID == end.getCityID()) {
                exitTheVehicle += passengerArray[i].numberOfPassengers;
                income += ((double)passengerArray[i].numberOfPassengers) * ((double)passengerArray[i].getDistance() * 0.2);
                passengerArray[i] = null;
            }
        }

        passengerList.clear();
        for (int i = 0; i < passengerArray.length; i++) {
            if (passengerArray[i] != null) {
                passengerList.add(passengerArray[i]);
            }
        }
        //Exit the vehicle end


        //Enter the vehicle start
        end.getPassengerList().trimToSize();
        passengerArray = new Passenger[end.getPassengerList().size()];
        for (int i = 0; i < end.getPassengerList().size(); i++) {
            passengerArray[i] = end.getPassengerList().get(i);
        }
        for (int i = 0; i < passengerArray.length; i++) {
            if (getEmptySpace() <= 0) {
                break;
            } else if (passengerArray[i].numberOfPassengers <= getEmptySpace()) {
                getPassengerList().add(passengerArray[i].clone(passengerArray[i].getDistance()));
                enterTheVehicle += passengerArray[i].numberOfPassengers;
                passengerArray[i] = null;
            } else if (passengerArray[i].numberOfPassengers > getEmptySpace()) {
                Passenger newPassenger = new Passenger(getEmptySpace(), passengerArray[i].startingCityID, passengerArray[i].destinationCityID);
                newPassenger.setDistance(passengerArray[i].getDistance());
                Passenger newPassenger2 = new Passenger(passengerArray[i].numberOfPassengers - getEmptySpace(), passengerArray[i].startingCityID, passengerArray[i].destinationCityID);
                newPassenger2.setDistance(passengerArray[i].getDistance());
                enterTheVehicle += getEmptySpace();
                getPassengerList().add(newPassenger);
                passengerArray[i] = newPassenger2;
            }
        }
        end.getPassengerList().clear();
        for (int i = 0; i < passengerArray.length; i++) {
            if (passengerArray[i] != null) {
                end.getPassengerList().add(passengerArray[i]);
            }
        }
        //Enter the vehicle end

        //ArrayList arrangement start
        passengerArray = new Passenger[passengerList.size()];
        for (int i = 0; i < passengerList.size(); i++) {
            passengerArray[i] = passengerList.get(i);
        }
        for (int i = 0; i < passengerArray.length; i++) {
            for (int j = i + 1; j < passengerArray.length; j++) {
                if(passengerArray[i] == null || passengerArray[j] == null)
                    continue;
                if (passengerArray[i].destinationCityID == passengerArray[j].destinationCityID) {
                    passengerArray[i].numberOfPassengers += passengerArray[j].numberOfPassengers;
                    passengerArray[j] = null;
                }
            }
        }
        passengerList.clear();
        for (int i = 0; i < passengerArray.length; i++) {
            if (passengerArray[i] != null) {
                passengerList.add(passengerArray[i]);
            }
        }
        //ArrayList arrangement end
        cellID = end.getCellID();
        PublicMethods publicMethods = new PublicMethods();
        publicMethods.enterTheVehicle = enterTheVehicle;
        publicMethods.exitTheVehicle = exitTheVehicle;
        publicMethods.income = income;
        return publicMethods;
    }

    public void startEngine(City start) {
        if (start == null)
            return;
        cellID = start.getCellID();
        int vehicleEmptySpace = getEmptySpace();
        while (!(start.getPassengerList().isEmpty() || vehicleEmptySpace == 0)) {
            if (start.getPassengerList().get(0).numberOfPassengers > vehicleEmptySpace) {
                Passenger newPassenger = new Passenger(vehicleEmptySpace, start.getPassengerList().get(0).startingCityID, start.getPassengerList().get(0).destinationCityID);
                newPassenger.setDistance(start.getPassengerList().get(0).getDistance());
                passengerList.add(newPassenger);
                start.getPassengerList().get(0).numberOfPassengers -= vehicleEmptySpace;
                vehicleEmptySpace = 0;
            } else {
                Passenger passenger = new Passenger(start.getPassengerList().get(0).numberOfPassengers, start.getPassengerList().get(0).startingCityID, start.getPassengerList().get(0).destinationCityID);
                passenger.setDistance(start.getPassengerList().get(0).getDistance());
                passengerList.add(passenger);
                vehicleEmptySpace -= passenger.numberOfPassengers;
                start.getPassengerList().remove(passenger);
            }
            start.getPassengerList().trimToSize();
        }
        //ArrayList arrangement start
        Passenger[] passengerArray = new Passenger[passengerList.size()];
        for (int i = 0; i < passengerList.size(); i++) {
            passengerArray[i] = passengerList.get(i);
        }
        for (int i = 0; i < passengerArray.length; i++) {
            for (int j = i + 1; j < passengerArray.length; j++) {
                if (passengerArray[i].destinationCityID == passengerArray[j].destinationCityID) {
                    passengerArray[i].numberOfPassengers += passengerArray[j].numberOfPassengers;
                    passengerArray[j] = null;
                }
            }
        }
        passengerList.clear();
        for (int i = 0; i < passengerArray.length; i++) {
            if (passengerArray[i] != null) {
                passengerList.add(passengerArray[i]);
            }
        }
        //ArrayList arrangement end

    }
    //Getters
    public int getCellID() {
        return cellID;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public ArrayList<Passenger> getPassengerList() {
        return passengerList;
    }

}