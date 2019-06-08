package pl.agh.soa.ejb.impl.message;

import pl.agh.soa.ejb.api.MessageManagerInterface;
import pl.agh.soa.model.SensorSignal;
import pl.agh.soa.model.Ticket;

import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@DependsOn({"ParkingSpotsStorage", "ParkingSpotsManager"})
public class MessageManager implements MessageManagerInterface {


    @Override
    public void handleTicket(Ticket ticket){

    }

    @Override
    public void handleSensorSignal(SensorSignal signal) {

    }
}
