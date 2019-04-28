package beans.jmsbeans;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashSet;

@Named
@ApplicationScoped
public class UserBean implements Serializable {

    @EJB(lookup = "java:module/MyMessageStorage")
    private MyMessageStorage messageStorage;

    private String login;
    private boolean notifications;

    private HashSet<String> observedBooks= new HashSet<>();

    public boolean isUnregistered(){
        if (login == null || login.length()==0){
            return true;
        }
        else return false;
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
    }

    public void readNews(){
        FacesContext context = FacesContext.getCurrentInstance();

        for (String message:messageStorage.getMessages()) {
            context.addMessage(null, new FacesMessage(message));
        }
        messageStorage.getMessages().clear();
    }

}
