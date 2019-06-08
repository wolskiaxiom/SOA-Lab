package pl.agh.soa.model;

public class ParkingSpot implements Comparable<ParkingSpot> {
    int idArea;
    int idSensor;
    boolean isOccupied;
    boolean isPaid;

    public ParkingSpot(int idArea, int idSensor, boolean isOccupied, boolean isPaid) {
        this.idArea = idArea;
        this.idSensor = idSensor;
        this.isOccupied = isOccupied;
        this.isPaid = isPaid;
    }

    public ParkingSpot() {
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public int getIdSensor() {
        return idSensor;
    }

    public void setIdSensor(int idSensor) {
        this.idSensor = idSensor;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o==null || getClass() != o.getClass()) return false;
        ParkingSpot parkingSpot = (ParkingSpot) o;
        return (parkingSpot.idArea==this.idArea && parkingSpot.idSensor==this.idSensor);
    }

    @Override
    public int hashCode(){
        return this.idArea;
    }


    @Override
    public int compareTo(ParkingSpot parkingSpot) {
        return (this.idArea - parkingSpot.idArea)*100+this.idSensor-parkingSpot.idSensor;
    }
}
