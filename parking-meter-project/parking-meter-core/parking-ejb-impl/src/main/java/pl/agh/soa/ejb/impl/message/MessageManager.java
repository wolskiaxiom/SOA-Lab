package pl.agh.soa.ejb.impl.message;

import pl.agh.soa.ejb.api.MessageManagerInterface;
import pl.agh.soa.ejb.exceptions.NoSuchNotificationException;
import pl.agh.soa.ejb.impl.storage.ParkingSpotsManager;
import pl.agh.soa.ejb.exceptions.NoSuchParkingSpotException;
import pl.agh.soa.model.SensorSignal;
import pl.agh.soa.model.Ticket;

import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@DependsOn({"ParkingSpotsStorage", "ParkingSpotsManager"})
public class MessageManager implements MessageManagerInterface {

    @EJB(lookup = "java:global/parking-ejb-impl-1.0/ParkingSpotsManager")
    ParkingSpotsManager parkingSpotsManager;

    @Override
    public void handleTicket(Ticket ticket) throws NoSuchParkingSpotException, NoSuchNotificationException {
        System.out.println("I am in message manager");
        parkingSpotsManager.handleTicket(ticket);
    }

    @Override
    public void handleSensorSignal(SensorSignal signal) throws NoSuchParkingSpotException, NoSuchNotificationException {
        parkingSpotsManager.handleSensorSignal(signal);
    }
}
