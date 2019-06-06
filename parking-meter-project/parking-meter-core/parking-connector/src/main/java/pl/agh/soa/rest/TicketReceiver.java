package pl.agh.soa.rest;

import pl.agh.soa.jms.publisher.MessagePublisher;
import pl.agh.soa.model.Ticket;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/receiver")
@Consumes("application/json")
@Stateless
public class TicketReceiver {

    @EJB(lookup = "java:global/parking_connector/MessagePublisher")
    MessagePublisher messagePublisher;

    @POST
    @Path("tickets")
    public Response addTicket(Ticket ticket){
        System.out.println(ticket.toString());
        try {
            messagePublisher.sendMessageFromSensor(1,1,1);
        }catch (NullPointerException npe){
            npe.printStackTrace();
            return Response.status(401).build();
        }
        return Response.status(201).build();
    }
}
