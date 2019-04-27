import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.jms.Topic;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Named
@SessionScoped
public class ConsumerManager implements Serializable {

    private String name;
    private UserMessages userMessages = new UserMessages();

    @EJB(lookup="java:global/ejb-topic-service-impl-1.0-SNAPSHOT/TopicForumServiceImpl")
    private TopicForumService topicForumService;


    public void register(String topicName) throws JMSException {
        ConsumerMessageListener listener = new ConsumerMessageListener(name,userMessages);
        topicForumService.registerConsumer(topicName, listener);
    }

    public void performSub(String topicName) {
        try {
            register(topicName);
            succesfullyRegistered();
        } catch (JMSException e) {
            errorAlreadyRegistered();
        }
    }

    public void unregister(String topicName, String consumerName) throws JMSException {
        topicForumService.unRegisterConsumer(topicName, consumerName);
    }

    public void performUnsub(String topicName){
        try {
            unregister(topicName, name);
            succesfullyUnregistered();
        }catch (Exception e){
            errorNotRegisteredYet();
        }
    }

    public void resetOutput(){
        userMessages.getMessages().clear();
    }

    public List<String> getTopics() throws JMSException {
        List<String> topics = new LinkedList<>();
        for(Topic topic: topicForumService.findAllTopics()){
            topics.add(topic.getTopicName());
        }
        return topics;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public ArrayList<String> getMessages() {
        return userMessages.getMessages();
    }

    public void setMessages(ArrayList<String> messages) {
        this.userMessages.setMessages(messages);
    }
    public void refresh(){
        System.out.println("refreshing"+userMessages.getMessages());
    }

    public void errorAlreadyRegistered() {
        System.out.println("errorAlreadyRegistered");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Error!", "You are already registered"));
    }
    public void errorNotRegisteredYet(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                "Warn!", "You are not registered yet"));
    }
    public void succesfullyRegistered(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Congrats!", "You are successfully registered!"));
    }
    public void succesfullyUnregistered(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Congrats!", "You are successfully unregistered!"));
    }
}
