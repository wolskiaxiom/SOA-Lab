package pl.agh.soa.dashboard.beans;

import pl.agh.soa.ejb.storage.ParkingSpotsManagerInterface;
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
@DependsOn({"ParkingSpotsStorage", "ParkingSpotsManager"})
public class DashboardBean implements Serializable {

    @EJB(lookup = "java:global/parking-ejb-impl-1.0/ParkingSpotsManager")
    ParkingSpotsManagerInterface parkingSpotsManager;

    public TreeSet<ParkingSpot> getSpots(){
        return parkingSpotsManager.getSpotsForDashboard();
    }

    public int getNumberOfSpots(){
        return parkingSpotsManager.countPlaces();
    }

    public int getNumberOfOccupiedPlaces(){
        return parkingSpotsManager.countOccupiedPlaces();
    }
}
