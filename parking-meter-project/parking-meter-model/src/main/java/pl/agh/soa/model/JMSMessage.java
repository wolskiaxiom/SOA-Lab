package pl.agh.soa.model;

import java.io.Serializable;

public interface JMSMessage extends Serializable {
    boolean isItTicket();
}
