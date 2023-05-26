package package1;

import com.example.victorytransport.PublicMethods;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Level {
    ArrayList<Object> levelObjects = new ArrayList<Object>();
    public Object[] levelMap = new Object[101];
    public Level() {
    }
    public Level(File txtFile) {
        createLevel(txtFile, new CityArrangement());
    }

   //Creating level
    public void createLevel(File txtFile,CityArrangement arrangementObject) {
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(txtFile);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
        }
        while(fileReader.hasNextLine()) {
            String objectData = fileReader.nextLine();
            int whereIsComma= objectData.indexOf(",");
            String objectType = objectData.substring(0, whereIsComma);
            objectData = objectData.substring(whereIsComma+1);
            whereIsComma = objectData.indexOf(",");


            if(objectType.equals("City")) {
                String cityName = objectData.substring(0,whereIsComma);
                objectData = objectData.substring(whereIsComma+1);
                whereIsComma = objectData.indexOf(",");


                int cellID = Integer.parseInt(objectData.substring(0, whereIsComma));
                objectData = objectData.substring(whereIsComma+1);

                int cityID = Integer.parseInt(objectData);
                //Adding cities to the switch
                switch (cityName) {
                    case "Istanbul" -> {
                        City Istanbul = arrangementObject.Istanbul.clone();
                        Istanbul.cityArrangment(cellID, cityID);
                        levelObjects.add(Istanbul);
                    }
                    case "Van" -> {
                        City Van = arrangementObject.Van.clone();
                        Van.cityArrangment(cellID, cityID);
                        levelObjects.add(Van);
                    }
                    case "Mersin" -> {
                        City Mersin = arrangementObject.Mersin.clone();
                        Mersin.cityArrangment(cellID, cityID);
                        levelObjects.add(Mersin);
                    }
                    case "Izmir" -> {
                        City Izmir = arrangementObject.Izmir.clone();
                        Izmir.cityArrangment(cellID, cityID);
                        levelObjects.add(Izmir);
                    }
                    case "Ankara" -> {
                        City Ankara = arrangementObject.Ankara.clone();
                        Ankara.cityArrangment(cellID, cityID);
                        levelObjects.add(Ankara);
                    }
                    case "Antalya" -> {
                        City Antalya = arrangementObject.Antalya.clone();
                        Antalya.cityArrangment(cellID, cityID);
                        levelObjects.add(Antalya);
                    }
                    case "Bursa" -> {
                        City Bursa = arrangementObject.Bursa.clone();
                        Bursa.cityArrangment(cellID, cityID);
                        levelObjects.add(Bursa);
                    }
                    case "Konya" -> {
                        City Konya = arrangementObject.Konya.clone();
                        Konya.cityArrangment(cellID, cityID);
                        levelObjects.add(Konya);
                    }
                    case "Mus" -> {
                        City Mus = arrangementObject.Mus.clone();
                        Mus.cityArrangment(cellID, cityID);
                        levelObjects.add(Mus);
                    }
                    case "Rize" -> {
                        City Rize = arrangementObject.Rize.clone();
                        Rize.cityArrangment(cellID, cityID);
                        levelObjects.add(Rize);
                    }
                    case "Mugla" -> {
                        City Mugla = arrangementObject.Mugla.clone();
                        Mugla.cityArrangment(cellID, cityID);
                        levelObjects.add(Mugla);
                    }
                    case "Erzincan" -> {
                        City Erzincan = arrangementObject.Erzincan.clone();
                        Erzincan.cityArrangment(cellID, cityID);
                        levelObjects.add(Erzincan);
                    }
                    case "Tekirdag" -> {
                        City Tekirdag = arrangementObject.Tekirdag.clone();
                        Tekirdag.cityArrangment(cellID, cityID);
                        levelObjects.add(Tekirdag);
                    }
                    case "Adana" -> {
                        City Adana = arrangementObject.Adana.clone();
                        Adana.cityArrangment(cellID, cityID);
                        levelObjects.add(Adana);
                    }
                    case "Aydin" -> {
                        City Aydin = arrangementObject.Aydin.clone();
                        Aydin.cityArrangment(cellID, cityID);
                        levelObjects.add(Aydin);
                    }
                    case "Usak" -> {
                        City Usak = arrangementObject.Usak.clone();
                        Usak.cityArrangment(cellID, cityID);
                        levelObjects.add(Usak);
                    }
                    case "Manisa" -> {
                        City Manisa = arrangementObject.Manisa.clone();
                        Manisa.cityArrangment(cellID, cityID);
                        levelObjects.add(Manisa);
                    }
                    case "Bolu" -> {
                        City Bolu = arrangementObject.Bolu.clone();
                        Bolu.cityArrangment(cellID, cityID);
                        levelObjects.add(Bolu);
                    }
                    case "Kars" -> {
                        City Kars = arrangementObject.Kars.clone();
                        Kars.cityArrangment(cellID, cityID);
                        levelObjects.add(Kars);
                    }
                    case "Ordu" -> {
                        City Ordu = arrangementObject.Ordu.clone();
                        Ordu.cityArrangment(cellID, cityID);
                        levelObjects.add(Ordu);
                    }
                    case "Elazig" -> {
                        City Elazig = arrangementObject.Elazig.clone();
                        Elazig.cityArrangment(cellID, cityID);
                        levelObjects.add(Elazig);
                    }
                    default -> {
                    }
                }

            }
            else if(objectType.equals("Passenger")) {
                int numberOfPassengers = Integer.parseInt(objectData.substring(0,whereIsComma));
                objectData = objectData.substring(whereIsComma+1);
                whereIsComma = objectData.indexOf(",");

                int startingCityID=Integer.parseInt(objectData.substring(0,whereIsComma));
                objectData = objectData.substring(whereIsComma+1);
                whereIsComma = objectData.indexOf(",");

                int destinationCityID=Integer.parseInt(objectData);

                levelObjects.add(new Passenger(numberOfPassengers,startingCityID,destinationCityID));
            }
            else if(objectType.equals("Vehicle")) {
                int currentCityID=Integer.parseInt(objectData.substring(0,whereIsComma));
                objectData = objectData.substring(whereIsComma+1);
                whereIsComma = objectData.indexOf(",");

                int passengerCapacity=Integer.parseInt(objectData);
                levelObjects.add(new Vehicle(currentCityID,passengerCapacity));
            }
            else if(objectType.equals("Fixed")) {
                int cellID=Integer.parseInt(objectData);
                levelObjects.add(new Fixed(cellID));
            }
        }//while end

        for (Object obj : levelObjects) {
            if(obj instanceof City) {
                levelMap[((City)obj).getCellID()] = obj;
                //levelMap.add(((City)obj).getCellID(), obj); this is for arrayList
            }
            if(obj instanceof Vehicle) {//buna bak
                levelMap[0] = obj;
                //levelMap.add(((Vehicle)obj).getCellID(), obj);	this is for arrayList
            }
            if(obj instanceof Fixed) {
                levelMap[((Fixed)obj).getCellID()] = obj;
                //levelMap.add(((Fixed)obj).getCellID(), obj);	this is for arrayList
            }

        }

        PublicMethods publicMethods= new PublicMethods();
        for(Object obj :levelObjects){
            if(obj instanceof Passenger) {
                for(int i =1;i<levelMap.length;i++){
                    if(levelMap[i] instanceof City)
                        if(((City) levelMap[i]).getCityID()==((Passenger)obj).getStartingCityID()){
                            ((City) levelMap[i]).getPassengerList().add((Passenger)obj);
                            int startingCellID = publicMethods.returnCityCell(levelMap,((Passenger)obj).getStartingCityID());
                            int endCellID = publicMethods.returnCityCell(levelMap,((Passenger)obj).getDestinationCityID());
                            ((Passenger)obj).setDistance(publicMethods.calculatingDistance(startingCellID,endCellID));
                        }
                }
            }
        }

    }

}
