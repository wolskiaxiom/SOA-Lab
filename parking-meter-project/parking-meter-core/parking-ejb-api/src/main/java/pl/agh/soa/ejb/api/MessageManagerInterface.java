package pl.agh.soa.ejb.api;

import javax.ejb.Local;
import javax.ejb.Remote;

@Remote
public interface MessageManagerInterface {
    void doSomething();
}
