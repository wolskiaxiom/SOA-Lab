package pl.agh.soa.dashboard.beans;

import pl.agh.soa.ejb.storage.ParkingSpotsStorageInterface;
import pl.agh.soa.model.ParkingSpot;

import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.TreeSet;

@SessionScoped
@Named
@DependsOn({"ParkingSpotsStorage"})
public class DashboardBean implements Serializable {

    TreeSet<ParkingSpot> allSpots = new TreeSet<>();
    TreeSet<ParkingSpot> spots = new TreeSet<>();

    @EJB(lookup = "java:global/parking-ejb-impl-1.0/ParkingSpotsStorage")
    ParkingSpotsStorageInterface parkingSpotsStorage;

    public TreeSet<ParkingSpot> getAllSpots() {
        allSpots = parkingSpotsStorage.getSpots();
        return allSpots;
    }

    public TreeSet<ParkingSpot> getSpots(){
        return parkingSpotsStorage.getSpotsForDashboard();
    }

    public int getNumberOfSpots(){
        return parkingSpotsStorage.countPlaces();
    }

    public int getNumberOfOccupiedPlaces(){
        return parkingSpotsStorage.countOccupiedPlaces();
    }
}
