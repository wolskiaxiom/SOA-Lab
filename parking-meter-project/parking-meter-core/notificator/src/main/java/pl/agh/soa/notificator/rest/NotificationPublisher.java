package pl.agh.soa.notificator.rest;

import pl.agh.soa.ejb.exceptions.NoSuchParkingSpotException;
import pl.agh.soa.ejb.notification.NotificationQueueInterface;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/")
@Produces("application/json")
@Stateless
public class NotificationPublisher {

    @EJB(lookup = "java:global/parking-ejb-impl-1.0/NotificationsQueue")
    NotificationQueueInterface notificationsQueue;

    @GET
    @Path("notifications")
    public Response getNotifications() throws NoSuchParkingSpotException {
        return Response.status(200).entity(notificationsQueue.getNotifications()).build();
    }

}
