package beans.jmsbeans;

import controllers.BookController;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

@Named
@SessionScoped
public class UserBean implements Serializable {

    @EJB(lookup = "java:module/MyMessageStorage")
    private MyMessageStorage messageStorage;

    private String login;
    private boolean notifications;

    private HashSet<Long> observedBooks= new HashSet<>();

    public void subscribe(long idBook){
        if (isBookSubscribed(idBook)){
            observedBooks.remove(idBook);
        }else {
            observedBooks.add(idBook);
        }
    }

    public boolean isUnregistered(){
        if (login == null || login.length()==0){
            return true;
        }
        else return false;
    }

    public boolean isBookSubscribed(Long bookId){
        return observedBooks.contains(bookId);
    }

    public UserBean() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public HashSet<Long> getObservedBooks() {
        return observedBooks;
    }

    public void setObservedBooks(HashSet<Long> observedBooks) {
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

    public void readNews() throws JMSException {
        FacesContext context = FacesContext.getCurrentInstance();

        for (TextMessage message:messageStorage.getMessages()) {
            if(message.getStringProperty("type").equals("add_book") && notifications){
                context.addMessage(null, new FacesMessage(message.getText()));
            }else if(message.getStringProperty("type").equals("book_available")){
                try {
                    long bookId = message.getLongProperty("bookId");
                    if(isBookSubscribed(bookId)){
                        String title = BookController.getBookById(bookId).getTitle();
                        context.addMessage(null, new FacesMessage(title + " " + message.getText() + new Date()));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        messageStorage.getMessages().clear();
    }

}
