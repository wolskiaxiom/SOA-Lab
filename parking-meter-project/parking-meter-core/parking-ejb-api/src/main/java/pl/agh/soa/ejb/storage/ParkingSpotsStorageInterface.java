package pl.agh.soa.ejb.storage;

import pl.agh.soa.ejb.exceptions.NoSuchParkingSpotException;
import pl.agh.soa.model.Notification;
import pl.agh.soa.model.ParkingSpot;
import pl.agh.soa.model.SensorSignal;
import pl.agh.soa.model.Ticket;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Remote;
import java.util.TreeSet;

@Remote
public interface ParkingSpotsStorageInterface {
    void updateParkingSpot(SensorSignal sensorSignal) throws NoSuchParkingSpotException;

    void updateParkingSpot(Ticket ticket) throws NoSuchParkingSpotException;

    TreeSet<ParkingSpot> getSpots();

    @Lock(LockType.WRITE)
    void updateParkingSpot(Notification notification) throws NoSuchParkingSpotException;
}
