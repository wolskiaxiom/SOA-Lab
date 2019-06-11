package pl.agh.soa.ejb.impl.storage;

import pl.agh.soa.datasource.database.UserDetailsManager;
import pl.agh.soa.datasource.entities.User;
import pl.agh.soa.datasource.entities.UserDetails;
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
import java.security.Principal;
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

    @RolesAllowed({"Employee"})
    private TreeSet<ParkingSpot> getSpotsFromConcreteArea(int areaId){
        TreeSet<ParkingSpot> specificAreaSpots = new TreeSet<>();
        Iterator it = spots.iterator();
        while (it.hasNext()){
            ParkingSpot spot = (ParkingSpot) it.next();
            if(spot.getIdArea()==areaId)specificAreaSpots.add(spot);
        }
        return specificAreaSpots;
    }

    @Override
    @RolesAllowed({"Manager","Employee"})
    public TreeSet<ParkingSpot> getSpotsForDashboard(){
        Principal principal = ctx.getCallerPrincipal();
        if (ctx.isCallerInRole("Manager")){
            return getSpots();
        }else if(ctx.isCallerInRole("Employee")){
            UserDetails details = UserDetailsManager.getUserDetails(principal.getName());
            return getSpotsFromConcreteArea(details.getAreaId());
        }else return null;
    }

    @RolesAllowed({"Manager"})
    private int countAllSpots(){
        return spots.size();
    }

    @RolesAllowed({"Employee"})
    private int countAllSpotsInArea(int areaId){
        int count = 0;
        Iterator it = spots.iterator();
        while (it.hasNext()){
            ParkingSpot parkingSpot = (ParkingSpot) it.next();
            if(parkingSpot.getIdArea()==areaId) count++;
        }
        return count;
    }

    @Override
    @RolesAllowed({"Manager", "Employee"})
    public int countPlaces(){
        Principal principal = ctx.getCallerPrincipal();
        if(ctx.isCallerInRole("Manager")){
            return countAllSpots();
        }else if(ctx.isCallerInRole("Employee")){
            UserDetails details = UserDetailsManager.getUserDetails(principal.getName());
            return countAllSpotsInArea(details.getAreaId());
        }else return 0;
    }

    @RolesAllowed({"Manager"})
    private int countAllOccupiedPlaces(){
        return (int) spots.stream().filter(ParkingSpot::getIsOccupied).count();
    }

    @RolesAllowed({"Employee"})
    private int countAllOccupiedPlacesInArea(int idArea){
        return (int) spots.stream()
                .filter(parkingSpot -> parkingSpot.getIdArea()==idArea)
                .filter(ParkingSpot::getIsOccupied).count();
    }

    @Override
    @RolesAllowed({"Manager", "Employee"})
    public int countOccupiedPlaces(){
        Principal principal = ctx.getCallerPrincipal();
        if(ctx.isCallerInRole("Manager")){
            return countAllOccupiedPlaces();
        }else if(ctx.isCallerInRole("Employee")){
            UserDetails details = UserDetailsManager.getUserDetails(principal.getName());
            return countAllOccupiedPlacesInArea(details.getAreaId());
        }
        return 0;
    }
}
