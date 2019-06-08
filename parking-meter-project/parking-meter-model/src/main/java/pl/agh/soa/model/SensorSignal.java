package pl.agh.soa.model;

import java.io.Serializable;

public class SensorSignal implements Serializable, JMSMessage {
    private boolean isOccupied;
    private int areaId;
    private int sensorId;
    private long detectionTime;

    public SensorSignal() {
    }

    public SensorSignal(boolean isOccupied, int areaId, int sensorId, long detectionTime) {
        this.isOccupied = isOccupied;
        this.areaId = areaId;
        this.sensorId = sensorId;
        this.detectionTime = detectionTime;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
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

    public long getDetectionTime() {
        return detectionTime;
    }

    public void setDetectionTime(long detectionTime) {
        this.detectionTime = detectionTime;
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
