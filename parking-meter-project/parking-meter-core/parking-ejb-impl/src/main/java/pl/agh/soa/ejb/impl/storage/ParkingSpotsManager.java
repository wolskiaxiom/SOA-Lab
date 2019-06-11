package pl.agh.soa.ejb.impl.storage;


import pl.agh.soa.datasource.database.UserDetailsManager;
import pl.agh.soa.datasource.entities.UserDetails;
import pl.agh.soa.ejb.storage.ParkingSpotsManagerInterface;
import pl.agh.soa.ejb.storage.ParkingSpotsStorageInterface;
import pl.agh.soa.model.ParkingSpot;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.*;
import java.security.Principal;
import java.util.Iterator;
import java.util.TreeSet;

@Stateless
@Startup
@DependsOn({"ParkingSpotsStorage"})
public class ParkingSpotsManager implements ParkingSpotsManagerInterface {

    @EJB(lookup = "java:global/parking-ejb-impl-1.0/ParkingSpotsStorage")
    ParkingSpotsStorageInterface parkingSpotsStorage;

    @Resource
    SessionContext ctx;

    @RolesAllowed({"Manager"})
    @Lock(LockType.READ)
    public TreeSet<ParkingSpot> getSpots() {
        return parkingSpotsStorage.getSpots();
    }

    @RolesAllowed({"Employee"})
    @Lock(LockType.READ)
    private TreeSet<ParkingSpot> getSpotsFromConcreteArea(int areaId){
        TreeSet<ParkingSpot> specificAreaSpots = new TreeSet<>();
        Iterator it = parkingSpotsStorage.getSpots().iterator();
        while (it.hasNext()){
            ParkingSpot spot = (ParkingSpot) it.next();
            if(spot.getIdArea()==areaId)specificAreaSpots.add(spot);
        }
        return specificAreaSpots;
    }

    @Override
    @RolesAllowed({"Manager","Employee"})
    @Lock(LockType.READ)
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
    @Lock(LockType.READ)
    private int countAllSpots(){
        return parkingSpotsStorage.getSpots().size();
    }

    @RolesAllowed({"Employee"})
    @Lock(LockType.READ)
    private int countAllSpotsInArea(int areaId){
        int count = 0;
        Iterator it = parkingSpotsStorage.getSpots().iterator();
        while (it.hasNext()){
            ParkingSpot parkingSpot = (ParkingSpot) it.next();
            if(parkingSpot.getIdArea()==areaId) count++;
        }
        return count;
    }

    @Override
    @RolesAllowed({"Manager", "Employee"})
    @Lock(LockType.READ)
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
    @Lock(LockType.READ)
    private int countAllOccupiedPlaces(){
        return (int) parkingSpotsStorage.getSpots().stream().filter(ParkingSpot::getIsOccupied).count();
    }

    @RolesAllowed({"Employee"})
    @Lock(LockType.READ)
    private int countAllOccupiedPlacesInArea(int idArea){
        return (int) parkingSpotsStorage.getSpots().stream()
                .filter(parkingSpot -> parkingSpot.getIdArea()==idArea)
                .filter(ParkingSpot::getIsOccupied).count();
    }

    @Override
    @RolesAllowed({"Manager", "Employee"})
    @Lock(LockType.READ)
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
