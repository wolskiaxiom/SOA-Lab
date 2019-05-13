package pl.agh.kis.soa.rest;

import org.jboss.resteasy.links.AddLinks;
import org.jboss.resteasy.links.LinkResource;
import pl.agh.kis.soa.entitiesManagers.MovieManager;
import pl.agh.kis.soa.entitiesManagers.UserManager;
import pl.agh.kis.soa.entity.Movie;
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
    @Path("users/{id}")
    public Response getUser(@PathParam("id") long id){
        User user = UserManager.getUser(id);
        return Response.status(200).entity(user).build();
    }

    @LinkResource
    @POST
    @Path("users")
    public Response addUser(User user){
        UserManager.addUser(user);
        return Response.status(201).build();
    }

    @LinkResource
    @PUT
    @Path("users/{id}")
    public Response updateUser(@PathParam("id") long id, User user){
        user.setUserId(id);
        UserManager.updateUser(user);
        return Response.status(201).build();
    }

    @LinkResource(value = User.class)
    @DELETE
    @Path("users/{id}")
    public Response deleteUser(@PathParam("id") long id){
        try {
            UserManager.deleteUser(id);
        }catch (Exception e){
            return Response.status(405).build();
        }
        return Response.status(200).build();
    }

    @LinkResource
    @POST
    @Path("users/{id}/movies")
    public Response addUserMovie(@PathParam("id") long id, Movie movie){
        User user = UserManager.getUser(id);
        user.getMovies().add(movie);
        movie.getUsers().add(user);
        UserManager.updateUser(user);
        return Response.status(201).build();
    }

    @LinkResource
    @DELETE
    @Path("users/{userid}/movies/{movieid}")
    public Response deleteUserMovie(@PathParam("userid") long userId, @PathParam("movieid") long movieId){
        Movie movie = MovieManager.getMovie(movieId);
        User user = UserManager.getUser(userId);
        user.getMovies().remove(movie);
        UserManager.updateUser(user);
        return Response.status(201).build();
    }
}
