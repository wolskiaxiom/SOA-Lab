package pl.agh.kis.soa.rest;

import org.jboss.resteasy.links.AddLinks;
import org.jboss.resteasy.links.LinkResource;
import pl.agh.kis.soa.entitiesManagers.UserManager;
import pl.agh.kis.soa.entity.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
@Consumes("application/json")
@Produces("application/json")
public class UserController {

    @AddLinks
    @LinkResource(value = User.class)
    @GET
    @Path("users")
    public Response getUsers(){
        List<User> users = UserManager.readAllUsers();
        return Response.status(200).entity(users).build();
    }

    @AddLinks
    @LinkResource
    @GET
    @Path("movie/{id}")
    public Response getUser(@PathParam("id") long id){
        User user = UserManager.getUser(id);
        return Response.status(200).entity(user).build();
    }

    @LinkResource
    @POST
    @Path("movie")
    public void addUser(User user){

    }
}
