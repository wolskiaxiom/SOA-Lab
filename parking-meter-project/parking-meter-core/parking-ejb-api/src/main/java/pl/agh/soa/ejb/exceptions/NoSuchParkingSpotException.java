package pl.agh.soa.ejb.exceptions;

public class NoSuchParkingSpotException extends Exception {
    public NoSuchParkingSpotException(){}
    public NoSuchParkingSpotException(String s) {
        super(s);
    }
}
