package pl.agh.soa.ejb.impl.notification;

import pl.agh.soa.ejb.exceptions.NoSuchNotificationException;
import pl.agh.soa.model.Notification;
import pl.agh.soa.model.SensorSignal;
import pl.agh.soa.model.Ticket;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.*;

@Singleton
@Startup
public class NotificationsQueue {

    private LinkedList<Notification> notifications = new LinkedList<>();

    public void addNotification(SensorSignal sensorSignal) {
        Notification newNotification = new Notification(sensorSignal);
        notifications.add(newNotification);
        this.validateNotifications();
    }


    public void deleteNotification(SensorSignal sensorSignal) throws NoSuchNotificationException{
        Notification notification = new Notification(sensorSignal);
        if(!notifications.remove(notification)){
            throw new NoSuchNotificationException("There is no notification related to specified Parking Place") ;
        }
        deleteObsoleteNotifications();
    }

    public void extendLegalStaying(Ticket ticket) throws NoSuchNotificationException{
        System.out.println("I am extending");
        Notification notification = new Notification(ticket);
        if (!notifications.remove(notification))
            throw new NoSuchNotificationException("There is no notification related to specified Parking Place");

        notifications.add(notification);
        validateNotifications();
        System.out.println(this.toString());
    }

    public void validateNotifications(){
        Collections.sort(notifications);
        this.deleteObsoleteNotifications();
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

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        Iterator<Notification> notificationIterator = notifications.iterator();
        for (Notification n: notifications){
            buffer.append(n.toString()+"\n");
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws InterruptedException {
        NotificationsQueue queue = new NotificationsQueue();

        Notification n1 = new Notification(1,1,System.currentTimeMillis());
        Thread.sleep(10);
        Notification n2 = new Notification(2,2,System.currentTimeMillis());
        Thread.sleep(10);
        Notification n3 = new Notification(1,1,System.currentTimeMillis());
        queue.notifications.remove(n1);
        queue.notifications.add(n1);
        queue.notifications.remove(n2);
        queue.notifications.add(n2);
        queue.notifications.remove(n3);
        queue.notifications.add(n3);
        Collections.sort(queue.notifications);
        System.out.println(queue);
//        queue.deleteNotification(new SensorSignal(false,2,2,System.currentTimeMillis()));
        System.out.println(queue);
        queue.deleteObsoleteNotifications();
        System.out.println(queue);
    }

}
