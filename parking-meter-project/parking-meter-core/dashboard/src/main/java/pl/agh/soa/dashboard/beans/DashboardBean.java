package pl.agh.soa.dashboard.beans;

import pl.agh.soa.ejb.storage.ParkingSpotsStorageInterface;
import pl.agh.soa.model.ParkingSpot;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.TreeSet;

@SessionScoped
@Named
@DependsOn({"ParkingSpotsStorage"})
public class DashboardBean implements Serializable {

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
