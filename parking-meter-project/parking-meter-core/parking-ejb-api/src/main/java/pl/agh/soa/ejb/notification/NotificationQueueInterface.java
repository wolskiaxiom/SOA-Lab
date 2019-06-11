package pl.agh.soa.ejb.notification;

import pl.agh.soa.ejb.exceptions.NoSuchNotificationException;
import pl.agh.soa.ejb.exceptions.NoSuchParkingSpotException;
import pl.agh.soa.model.Notification;
import pl.agh.soa.model.SensorSignal;
import pl.agh.soa.model.Ticket;

import javax.ejb.Remote;
import java.util.LinkedList;

@Remote
public interface NotificationQueueInterface {

    void addNotification(SensorSignal sensorSignal) throws NoSuchParkingSpotException;

    void deleteNotification(SensorSignal sensorSignal) throws NoSuchNotificationException, NoSuchParkingSpotException;

    void extendLegalStaying(Ticket ticket) throws NoSuchNotificationException, NoSuchParkingSpotException;

    LinkedList<Notification> getNotifications() throws NoSuchParkingSpotException;
}
