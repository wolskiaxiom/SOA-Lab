package pl.agh.soa.notificator.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/notificator")
public class NotificatorRestApplication extends Application {
    private Set<Class<?>> classes = new HashSet<>();

    public NotificatorRestApplication() {
        classes.add(NotificationPublisher.class);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}
