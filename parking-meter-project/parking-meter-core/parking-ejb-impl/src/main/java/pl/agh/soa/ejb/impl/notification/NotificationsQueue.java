package pl.agh.soa.ejb.impl.notification;

import pl.agh.soa.ejb.exceptions.NoSuchNotificationException;
import pl.agh.soa.ejb.exceptions.NoSuchParkingSpotException;
import pl.agh.soa.ejb.notification.NotificationQueueInterface;
import pl.agh.soa.ejb.storage.ParkingSpotsStorageInterface;
import pl.agh.soa.model.Notification;
import pl.agh.soa.model.SensorSignal;
import pl.agh.soa.model.Ticket;

import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.*;

@Singleton
@Startup
@DependsOn({"ParkingSpotsStorage"})
public class NotificationsQueue implements NotificationQueueInterface {

    @EJB(lookup = "java:global/parking-ejb-impl-1.0/ParkingSpotsStorage")
    ParkingSpotsStorageInterface parkingSpotsStorage;

    private LinkedList<Notification> notifications = new LinkedList<>();

    @Override
    public void addNotification(SensorSignal sensorSignal) throws NoSuchParkingSpotException {
        Notification newNotification = new Notification(sensorSignal);
        notifications.add(newNotification);
        this.validateNotifications();
        System.out.println(this);
    }

    @Override
    public void deleteNotification(SensorSignal sensorSignal) throws NoSuchNotificationException, NoSuchParkingSpotException {
        Notification notification = new Notification(sensorSignal);
        if(!notifications.remove(notification)){
            throw new NoSuchNotificationException("There is no notification related to specified Parking Place") ;
        }
        updateNotPaidParkingSpots();
        deleteObsoleteNotifications();
    }

    @Override
    public void extendLegalStaying(Ticket ticket) throws NoSuchNotificationException, NoSuchParkingSpotException {
        Notification notificationFromTicket = new Notification(ticket);
        Notification foundNotification = getConcreteNotificationFromNotifications(ticket);
        if (foundNotification==null)
            throw new NoSuchNotificationException("There is no notification related to specified Parking Place");
        notificationFromTicket.setExpiryTime(ticket.getMinutes()*60*1000+foundNotification.getExpiryTime());
        notifications.remove(foundNotification);
        notifications.add(notificationFromTicket);
        validateNotifications();
        System.out.println(this.toString());
    }

    private void validateNotifications() throws NoSuchParkingSpotException {
        Collections.sort(notifications);
        updateNotPaidParkingSpots();
        this.deleteObsoleteNotifications();
    }

    private Notification getConcreteNotificationFromNotifications(Ticket ticket){
        Notification notification = new Notification(ticket);
        Iterator iterator = notifications.iterator();
        while (iterator.hasNext()){
            Notification iteratedNotification = (Notification) iterator.next();
            if (notification.equals(iteratedNotification)){
                return iteratedNotification;
            }
        }
        return null;
    }

    private void deleteObsoleteNotifications(){
        Collections.sort(notifications);
        Iterator iterator = notifications.iterator();
        while(iterator.hasNext()){
            Notification notification = (Notification) iterator.next();
            if(notification.getExpiryTime() + 10*60*1000 < System.currentTimeMillis()){
                iterator.remove();
            }else{
                break;
            }
        }
    }

    private void updateNotPaidParkingSpots() throws NoSuchParkingSpotException {
        Iterator iterator = notifications.iterator();
        while (iterator.hasNext()){
            Notification notification = (Notification) iterator.next();
            if(notification.getExpiryTime() < System.currentTimeMillis()){
                parkingSpotsStorage.updateParkingSpot(notification);
            }else {
                break;
            }
        }

    }

    @Override
    public LinkedList<Notification> getNotifications() throws NoSuchParkingSpotException {
        validateNotifications();
        return notifications;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        Iterator<Notification> notificationIterator = notifications.iterator();
        for (Notification n: notifications){
            buffer.append(n.toString()+"\n");
        }
        return buffer.toString();
    }
}
