package pl.agh.soa.ejb.storage;

import pl.agh.soa.ejb.exceptions.NoSuchParkingSpotException;
import pl.agh.soa.model.ParkingSpot;
import pl.agh.soa.model.SensorSignal;
import pl.agh.soa.model.Ticket;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import java.util.TreeSet;

@Remote
public interface ParkingSpotsStorageInterface {
    void updateParkingSpot(SensorSignal sensorSignal) throws NoSuchParkingSpotException;

    void updateParkingSpot(Ticket ticket) throws NoSuchParkingSpotException;

    TreeSet<ParkingSpot> getSpots();

//    TreeSet<ParkingSpot> getSpotsFromConcreteArea(int areaId);

    TreeSet<ParkingSpot> getSpotsForDashboard();

    int countPlaces();

    int countOccupiedPlaces();
}
