package pl.agh.soa.ejb.exceptions;

public class NoSuchNotificationException extends Exception{

    public NoSuchNotificationException() {
    }

    public NoSuchNotificationException(String s) {
        super(s);
    }
}
