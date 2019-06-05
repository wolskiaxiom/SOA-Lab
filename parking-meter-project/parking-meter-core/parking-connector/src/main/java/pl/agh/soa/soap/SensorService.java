package pl.agh.soa.soap;

import pl.agh.soa.jms.publisher.NotificationPublisher;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class SensorService {

    @EJB(lookup = "java:app/parking_connector/NotificationPublisher")
    NotificationPublisher notificationPublisher;

    @WebMethod
    public void occupyPlace(int areaId, int sensorId){
        notificationPublisher.sendNotifaction(1,areaId,sensorId);
    }
    @WebMethod
    public void releasePlace(int areaId, int sensorId){
        notificationPublisher.sendNotifaction(2,areaId,sensorId);
    }



}
