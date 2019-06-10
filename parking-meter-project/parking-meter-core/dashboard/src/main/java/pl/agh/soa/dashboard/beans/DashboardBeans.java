package pl.agh.soa.dashboard.beans;

import pl.agh.soa.ejb.storage.ParkingSpotsStorageInterface;
import pl.agh.soa.model.ParkingSpot;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.TreeSet;

@ApplicationScoped
@Named
@DependsOn({"ParkingSpotsStorage"})
public class DashboardBeans {

    TreeSet<ParkingSpot> allSpots = new TreeSet<>();

    @EJB(lookup = "java:global/parking-ejb-impl-1.0/ParkingSpotsStorage")
    ParkingSpotsStorageInterface parkingSpotsStorage;

    public TreeSet<ParkingSpot> getAllSpots() {

        return allSpots;
    }

    public void doSomething(){
        parkingSpotsStorage.getSpots();
    }

    public void setAllSpots(TreeSet<ParkingSpot> allSpots) {
        this.allSpots = allSpots;
    }
}
