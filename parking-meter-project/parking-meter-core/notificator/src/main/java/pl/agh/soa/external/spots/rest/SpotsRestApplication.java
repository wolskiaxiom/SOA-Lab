package pl.agh.soa.external.spots.rest;


import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class SpotsRestApplication extends Application {

    private Set<Class<?>> classes = new HashSet<>();

    public SpotsRestApplication() {
        classes.add(SpotsPublisher.class);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

}
