package pl.agh.kis.soa.beans;

import org.primefaces.model.UploadedFile;
import pl.agh.kis.soa.entity.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class UsersBean implements Serializable {

//    @ManagedProperty(value = "#{restClient}")
//    private RestClient restClient;
    private Client client;

    private String name="";
    private Integer age=0;
    private UploadedFile file;

    private List<User> users=new ArrayList<>();

    public List<User> getUsers() {
        client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8080/MyRESTfulExample/users").request().accept(MediaType.APPLICATION_JSON_TYPE).get();
        if (response.getStatus()!= 200){
            throw new RuntimeException("Error");
        }
        users = response.readEntity(new GenericType<List<User>>(){});
        return users;
    }

    public void deleteUserMovie(Long userId, Long movieId){
        client = ClientBuilder.newClient();
        URI uri = UriBuilder.fromUri("http://localhost:8080/MyRESTfulExample/users/"+userId+"/movies/"+movieId).build();
        Response response = client.target(uri).request().delete();
        response.close();
    }

    public void addUser(){
        FacesMessage message;
        client = ClientBuilder.newClient();
        if (name==null || age==null){
            message = new FacesMessage("Zakończono niepomyślnie");
        }else {
            User user;
            if(file==null){
                user = new User(name,age,null);
            }else{
                user = new User(name,age,file.getContents());
            }
            Entity entity = Entity.entity(user, MediaType.APPLICATION_JSON);
            client.target("http://localhost:8080/MyRESTfulExample/users").request().post(entity).close();
             message = new FacesMessage("Zakończono pomyślnie");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }


    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
