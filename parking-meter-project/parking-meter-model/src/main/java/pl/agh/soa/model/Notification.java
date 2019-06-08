package pl.agh.soa.model;

import java.io.Serializable;

public class Notification implements Comparable<Notification>, Serializable {

    private int areaId;
    private int sensorId;
    private long expiryTime;

    public Notification() {
    }

    public Notification(int areaId, int sensorId, long expiryTime) {
        this.areaId = areaId;
        this.sensorId = sensorId;
        this.expiryTime = expiryTime;
    }

    public Notification(SensorSignal sensorSignal){
        this.areaId = sensorSignal.getAreaId();
        this.sensorId = sensorSignal.getSensorId();
        this.expiryTime = sensorSignal.getDetectionTime();
    }

    public Notification(Ticket ticket) {
        this.areaId = ticket.getAreaId();
        this.sensorId = ticket.getSensorId();
        this.expiryTime = ticket.getMinutes() * 60 * 1000;
    }

    @Override
    public int hashCode() {
        return areaId;
    }

    @Override
    public boolean equals(Object o) {
        if(this==o) return true;
        if(o==null || this.getClass()!=o.getClass())return false;
        Notification notification = (Notification) o;
        return (notification.areaId==this.areaId && notification.sensorId==this.sensorId);
    }

    @Override
    public int compareTo(Notification notification) {
        if (this.equals(notification)) {
            return 0;
        }
        return (int) (this.expiryTime-notification.expiryTime);
    }

    @Override
    public String toString() {
        return "NOTIFICATION! Area: " + areaId + " sensor: " + sensorId + " expire: " + expiryTime;
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

    public long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(long expiryTime) {
        this.expiryTime = expiryTime;
    }
}
