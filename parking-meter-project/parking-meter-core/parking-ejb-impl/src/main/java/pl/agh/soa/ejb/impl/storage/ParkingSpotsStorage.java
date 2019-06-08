package pl.agh.soa.ejb.impl.storage;

import pl.agh.soa.ejb.exceptions.NoSuchParkingSpotException;
import pl.agh.soa.model.ParkingSpot;
import pl.agh.soa.model.SensorSignal;
import pl.agh.soa.model.Ticket;

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

    public void updateParkingSpot(SensorSignal sensorSignal) throws NoSuchParkingSpotException {
        ParkingSpot parkingSpot = new ParkingSpot(sensorSignal);
        if (!exist(parkingSpot)) throw new NoSuchParkingSpotException();
        spots.add(parkingSpot);
    }

    public void updateParkingSpot(Ticket ticket) throws NoSuchParkingSpotException {
        ParkingSpot parkingSpot = new ParkingSpot(ticket);
        if(!exist(parkingSpot)) throw new NoSuchParkingSpotException("There is no such parking place!");
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
