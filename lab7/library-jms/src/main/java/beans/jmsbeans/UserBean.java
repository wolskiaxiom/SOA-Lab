package beans.jmsbeans;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashSet;

@Named
@ApplicationScoped
@Startup
public class UserBean implements Serializable {
    private String login;
    private boolean notifications;
    private HashSet<String> observedBooks= new HashSet<>();

    private FacesContext context;

    public boolean isUnregistered(){
        if (login == null || login.length()==0){
            return true;
        }
        else return false;
    }

    @PostConstruct
    private void dupa(){
        context = FacesContext.getCurrentInstance();
    }

    public FacesContext getContext() {
        return context;
    }

    public void setContext(FacesContext context) {
        this.context = context;
    }

    public UserBean() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public HashSet<String> getObservedBooks() {
        return observedBooks;
    }

    public void setObservedBooks(HashSet<String> observedBooks) {
        this.observedBooks = observedBooks;
    }

    public boolean isNotifications() {
        return notifications;
    }

    public void setNotifications(boolean notifications) {
        this.notifications = notifications;
    }

    public void succesfullyRegistered(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Congrats!", "You are successfully registered!"));

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Congrats!", "You are successfully registered!"));
    }
}
