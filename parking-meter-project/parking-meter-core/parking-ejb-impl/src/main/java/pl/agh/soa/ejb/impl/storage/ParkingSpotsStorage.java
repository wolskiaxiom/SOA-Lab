package pl.agh.soa.ejb.impl.storage;

import pl.agh.soa.ejb.exceptions.NoSuchParkingSpotException;
import pl.agh.soa.ejb.storage.ParkingSpotsStorageInterface;
import pl.agh.soa.model.ParkingSpot;
import pl.agh.soa.model.SensorSignal;
import pl.agh.soa.model.Ticket;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.*;

@Singleton
@Startup
@DeclareRoles({"Manager"})
public class ParkingSpotsStorage  implements ParkingSpotsStorageInterface {

    private TreeSet<ParkingSpot> spots;

    @Resource
    SessionContext ctx;

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

    @Override
    public void updateParkingSpot(SensorSignal sensorSignal) throws NoSuchParkingSpotException {
        ParkingSpot parkingSpot = new ParkingSpot(sensorSignal);
        if (!exist(parkingSpot)) throw new NoSuchParkingSpotException();
        spots.add(parkingSpot);
    }

    @Override
    public void updateParkingSpot(Ticket ticket) throws NoSuchParkingSpotException {
        ParkingSpot parkingSpot = new ParkingSpot(ticket);
        if(!exist(parkingSpot)) throw new NoSuchParkingSpotException("There is no such parking place!");
    }

    @Override
    @RolesAllowed({"Manager"})
    public TreeSet<ParkingSpot> getSpots() {
        return spots;
    }


    private boolean exist(ParkingSpot spot){
        return spots.contains(spot);
    }

    @Override
    public TreeSet<ParkingSpot> getSpotsFromConcreteArea(int areaId){
        TreeSet<ParkingSpot> specificAreaSpots = new TreeSet<>();
        Iterator it = spots.iterator();
        while (it.hasNext()){
            ParkingSpot spot = (ParkingSpot) it.next();
            if(spot.getIdArea()==areaId){
                specificAreaSpots.add(spot);
            }
        }
        return specificAreaSpots;
    }
}
