package pl.agh.soa.external.spots.rest;

import pl.agh.soa.ejb.storage.ParkingSpotsStorageInterface;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/")
@Produces("application/json")
@Stateless
public class SpotsPublisher {

    @EJB(lookup = "java:global/parking-ejb-impl-1.0/ParkingSpotsStorage")
    ParkingSpotsStorageInterface parkingSpotsStorage;

    @GET
    @Path("spots")
    public Response getSpots()
    {
        try{
            return Response.status(200).entity(parkingSpotsStorage.getSpots()).build();
        }catch (Exception e){
            return Response.status(400).build();
        }
    }
}
