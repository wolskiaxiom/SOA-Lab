package pl.agh.soa.ejb.impl.storage;


import pl.agh.soa.ejb.exceptions.NoSuchNotificationException;
import pl.agh.soa.ejb.impl.notification.NotificationsQueue;
import pl.agh.soa.ejb.exceptions.NoSuchParkingSpotException;
import pl.agh.soa.model.SensorSignal;
import pl.agh.soa.model.Ticket;

import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@DependsOn({"ParkingSpotsStorage"})
public class ParkingSpotsManager {

    @EJB(lookup = "java:global/parking-ejb-impl-1.0/ParkingSpotsStorage")
    ParkingSpotsStorage parkingSpotsStorage;

    @EJB(lookup = "java:global/parking-ejb-impl-1.0/NotificationsQueue")
    NotificationsQueue notificationsQueue;

    public void handleSensorSignal(SensorSignal sensorSignal) throws NoSuchParkingSpotException, NoSuchNotificationException {
        parkingSpotsStorage.updateParkingSpot(sensorSignal);
        if(sensorSignal.isOccupied()){
            notificationsQueue.addNotification(sensorSignal);
        }else{
            notificationsQueue.deleteNotification(sensorSignal);
        }
    }

    public void handleTicket(Ticket ticket) throws NoSuchParkingSpotException, NoSuchNotificationException {
        System.out.println("I am in psm");
        parkingSpotsStorage.updateParkingSpot(ticket);
        notificationsQueue.extendLegalStaying(ticket);
    }

}
