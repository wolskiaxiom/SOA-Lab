package pl.agh.soa.ejb.impl.storage;

import pl.agh.soa.model.ParkingSpot;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.*;

@Singleton
@Startup
public class ParkingSpotsStorage {

    private TreeSet<ParkingSpot> spots;

    @PostConstruct
    public void initialize(){
        spots = new TreeSet<>(Arrays.asList(
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

    public TreeSet<ParkingSpot> getSpots() {
        return spots;
    }

    public void setSpots(TreeSet<ParkingSpot> spots) {
        this.spots = spots;
    }

    public boolean exist(ParkingSpot spot){

        return spots.contains(spot);
    }
}
