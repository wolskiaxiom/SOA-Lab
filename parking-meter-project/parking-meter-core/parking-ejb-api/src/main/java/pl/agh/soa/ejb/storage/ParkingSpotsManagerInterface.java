package pl.agh.soa.ejb.storage;

import pl.agh.soa.model.ParkingSpot;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Remote;
import java.util.TreeSet;

@Remote
public interface ParkingSpotsManagerInterface {

    @RolesAllowed({"Manager","Employee"})
    @Lock(LockType.READ)
    TreeSet<ParkingSpot> getSpotsForDashboard();

    @RolesAllowed({"Manager", "Employee"})
    @Lock(LockType.READ)
    int countPlaces();

    @RolesAllowed({"Manager", "Employee"})
    @Lock(LockType.READ)
    int countOccupiedPlaces();
}
