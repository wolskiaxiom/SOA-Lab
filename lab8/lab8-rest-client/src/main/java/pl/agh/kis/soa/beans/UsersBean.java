package pl.agh.kis.soa.beans;

import pl.agh.kis.soa.entity.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;

@ManagedBean
@ViewScoped
public class UsersBean {

    @ManagedProperty(value = "#{restClient}")
    private RestClient restClient;

    private List<User> users;

    public List<User> getUsers() {
        Response response = restClient.getGETResponse("users");
        if (response.getStatus()!= 200){
            throw new RuntimeException("Error");
        }
        return users = response.readEntity(new GenericType<List<User>>(){});
    }
}
