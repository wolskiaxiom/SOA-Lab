package pl.agh.soa.model;

import java.io.Serializable;

public class SensorSignal implements Serializable, JMSMessage {
    private int isOccupied;
    private int areaId;
    private int sensorId;

    public SensorSignal() {
    }

    public SensorSignal(int isOccupied, int areaId, int sensorId) {
        this.isOccupied = isOccupied;
        this.areaId = areaId;
        this.sensorId = sensorId;
    }

    public int isOccupied() {
        return isOccupied;
    }

    public void setOccupied(int occupied) {
        isOccupied = occupied;
    }

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

    @Override
    public String toString() {
        return "Signal is occupied: "+isOccupied+" from area: "+ areaId+" and sensor: " + sensorId;
    }

    @Override
    public boolean isItTicket() {
        return false;
    }
}
