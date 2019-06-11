package pl.agh.soa.ejb.impl.message;

import pl.agh.soa.ejb.api.MessageManagerInterface;
import pl.agh.soa.ejb.notification.NotificationQueueInterface;
import pl.agh.soa.ejb.exceptions.NoSuchNotificationException;
import pl.agh.soa.ejb.impl.notification.NotificationsQueue;
import pl.agh.soa.ejb.impl.storage.ParkingSpotsManager;
import pl.agh.soa.ejb.exceptions.NoSuchParkingSpotException;
import pl.agh.soa.ejb.storage.ParkingSpotsStorageInterface;
import pl.agh.soa.model.SensorSignal;
import pl.agh.soa.model.Ticket;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.annotation.security.RunAs;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@DependsOn({"ParkingSpotsStorage"})
@RunAs("MessageManager")
@PermitAll
public class MessageManager implements MessageManagerInterface {


    @EJB(lookup = "java:global/parking-ejb-impl-1.0/ParkingSpotsStorage")
    ParkingSpotsStorageInterface parkingSpotsStorage;

    @EJB(lookup = "java:global/parking-ejb-impl-1.0/NotificationsQueue")
    NotificationQueueInterface notificationsQueue;

    @Override
    public void handleSensorSignal(SensorSignal sensorSignal) throws NoSuchParkingSpotException, NoSuchNotificationException {
        parkingSpotsStorage.updateParkingSpot(sensorSignal);
        if(sensorSignal.isOccupied()){
            System.out.println("Adding notification");
            notificationsQueue.addNotification(sensorSignal);
        }else{
            System.out.println("Deleting notification");
            notificationsQueue.deleteNotification(sensorSignal);
        }
    }
    @Override
    public void handleTicket(Ticket ticket) throws NoSuchParkingSpotException, NoSuchNotificationException {
        parkingSpotsStorage.updateParkingSpot(ticket);
        notificationsQueue.extendLegalStaying(ticket);
    }

}
