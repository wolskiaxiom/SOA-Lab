package pl.agh.soa.ejb.api;

import pl.agh.soa.ejb.exceptions.NoSuchNotificationException;
import pl.agh.soa.ejb.exceptions.NoSuchParkingSpotException;
import pl.agh.soa.model.SensorSignal;
import pl.agh.soa.model.Ticket;

import javax.ejb.Remote;

@Remote
public interface MessageManagerInterface {
    void handleTicket(Ticket ticket) throws NoSuchParkingSpotException, NoSuchNotificationException;
    void handleSensorSignal(SensorSignal signal) throws NoSuchParkingSpotException, NoSuchNotificationException;
}
