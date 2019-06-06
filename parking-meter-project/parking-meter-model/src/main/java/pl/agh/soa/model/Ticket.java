package pl.agh.soa.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ticket", propOrder = {"areaId", "sensorId", "minutes"})
public class Ticket implements Serializable, JMSMessage {
    private int areaId;
    private int sensorId;
    private int minutes;

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    @Override
    public String toString() {
        return "Ticket from area: " + areaId+ " sensor number: "+ sensorId + " for next: "+ minutes+ " minutes";
    }

    @Override
    public boolean isItTicket() {
        return true;
    }
}
