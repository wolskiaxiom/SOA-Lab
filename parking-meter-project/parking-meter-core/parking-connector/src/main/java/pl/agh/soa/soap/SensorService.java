package pl.agh.soa.soap;

import pl.agh.soa.jms.publisher.MessagePublisher;
import pl.agh.soa.model.SensorSignal;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class SensorService {

    @EJB(lookup = "java:app/parking_connector/MessagePublisher")
    MessagePublisher messagePublisher;

    @WebMethod
    public void occupyPlace(int areaId, int sensorId){
        SensorSignal sensorSignal = new SensorSignal(1,areaId,sensorId);
        messagePublisher.sendMessage(sensorSignal);
    }
    @WebMethod
    public void releasePlace(int areaId, int sensorId){
        SensorSignal sensorSignal = new SensorSignal(2,areaId,sensorId);
        messagePublisher.sendMessage(sensorSignal);
    }



}
