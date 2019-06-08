package pl.agh.soa.ejb.api;

import pl.agh.soa.model.SensorSignal;
import pl.agh.soa.model.Ticket;

import javax.ejb.Local;
import javax.ejb.Remote;

@Remote
public interface MessageManagerInterface {
    void handleTicket(Ticket ticket);
    void handleSensorSignal(SensorSignal signal);
}
