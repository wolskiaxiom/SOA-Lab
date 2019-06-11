package pl.agh.soa.ejb.impl.storage;

import pl.agh.soa.datasource.ParkingSpotsCreator;
import pl.agh.soa.ejb.exceptions.NoSuchParkingSpotException;
import pl.agh.soa.ejb.storage.ParkingSpotsStorageInterface;
import pl.agh.soa.model.Notification;
import pl.agh.soa.model.ParkingSpot;
import pl.agh.soa.model.SensorSignal;
import pl.agh.soa.model.Ticket;

import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.*;
import java.util.*;

@Singleton
@Startup
@DeclareRoles({"Manager","Employee"})
public class ParkingSpotsStorage  implements ParkingSpotsStorageInterface {

    private TreeSet<ParkingSpot> spots;



    @PostConstruct
    public void initialize(){
        spots = ParkingSpotsCreator.initializeSpots();
    }

    @PermitAll
    @Override
    @Lock(LockType.WRITE)
    public void updateParkingSpot(SensorSignal sensorSignal) throws NoSuchParkingSpotException {
        ParkingSpot parkingSpot = new ParkingSpot(sensorSignal);
        if (!exist(parkingSpot)) throw new NoSuchParkingSpotException();
        System.out.println(spots.remove(parkingSpot));
        System.out.println(spots.add(parkingSpot));
        System.out.println(this);
    }

    @PermitAll
    @Override
    @Lock(LockType.WRITE)
    public void updateParkingSpot(Ticket ticket) throws NoSuchParkingSpotException {
        ParkingSpot parkingSpot = new ParkingSpot(ticket);
        if(!exist(parkingSpot)) throw new NoSuchParkingSpotException("There is no such parking place!");
        spots.remove(parkingSpot);
        spots.add(parkingSpot);
    }

    @Override
    @RolesAllowed({"Manager", "Employee"})
    @Lock(LockType.READ)
    public TreeSet<ParkingSpot> getSpots() {
        return spots;
    }

    private boolean exist(ParkingSpot spot){
        return spots.contains(spot);
    }

    @Override
    @Lock(LockType.WRITE)
    @PermitAll
    public void updateParkingSpot(Notification notification) throws NoSuchParkingSpotException{
        ParkingSpot parkingSpot = new ParkingSpot(notification);
        if(!exist(parkingSpot)) throw new NoSuchParkingSpotException("There is no such parking place!");
        spots.remove(parkingSpot);
        spots.add(parkingSpot);
    }

    @Override
    public String toString(){
        StringBuffer buffer = new StringBuffer();
        Iterator iterator = spots.iterator();
        while (iterator.hasNext()){
            ParkingSpot parkingSpot = (ParkingSpot) iterator.next();
            buffer.append(parkingSpot+ "\n");
        }
        return buffer.toString();
    }
}
