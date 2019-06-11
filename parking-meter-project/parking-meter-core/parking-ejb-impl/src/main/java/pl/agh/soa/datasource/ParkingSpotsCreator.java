package pl.agh.soa.datasource;

import pl.agh.soa.model.ParkingSpot;

import java.util.Arrays;
import java.util.TreeSet;

public class ParkingSpotsCreator {
    public static TreeSet<ParkingSpot> initializeSpots(){
        return new TreeSet<>(Arrays.asList(
                new ParkingSpot(1,1,false,false),
                new ParkingSpot(1,2,false,false),
                new ParkingSpot(1,3,false,false),
                new ParkingSpot(1,4,false,false),
                new ParkingSpot(1,5,false,false),
                new ParkingSpot(2,1,false,false),
                new ParkingSpot(2,2,false,false),
                new ParkingSpot(2,3,false,false),
                new ParkingSpot(3,1,false,false),
                new ParkingSpot(3,2,false,false),
                new ParkingSpot(3,3,false,false),
                new ParkingSpot(3,4,false,false),
                new ParkingSpot(3,5,false,false),
                new ParkingSpot(3,6,false,false)
        ));
    }
}
