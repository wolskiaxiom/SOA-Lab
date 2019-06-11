package pl.agh.soa.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class RestApplication extends Application {
    private Set<Class<?>> classes = new HashSet<>();

    public RestApplication() {
        classes.add(TicketReceiver.class);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}
