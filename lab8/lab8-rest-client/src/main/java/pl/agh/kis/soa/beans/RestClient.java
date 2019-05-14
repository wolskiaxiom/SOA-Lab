package pl.agh.kis.soa.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class RestClient implements Serializable {

    private transient Client client;

    public String SERVICE_BASE_URI = "http://localhost:8080/MyRESTfulExample/";

    @PostConstruct
    protected void initialize(){
        client = ClientBuilder.newClient();
    }

    public String getStringResource(String relativeUrl){
        if(client==null){
            initialize();
        }
        return SERVICE_BASE_URI+relativeUrl;
    }

    public Response getGETResponse(String relativeUrl){
        return client.target(getStringResource(relativeUrl)).request().accept(MediaType.APPLICATION_JSON).get();
    }

}
