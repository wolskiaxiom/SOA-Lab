import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.jms.Topic;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Named
@SessionScoped
public class ConsumerManager implements Serializable {

    private String name;
    private String isSubscribed = "Unsubscribed";
    private String buttonValue = "Subscribe";
//    private ArrayList<String> messages = new ArrayList<String>(Arrays.asList("Welcome in ForRum!\n"));
    private UserMessages userMessages = new UserMessages();

    @EJB(lookup="java:global/ejb-topic-service-impl-1.0-SNAPSHOT/TopicForumServiceImpl")
    private TopicForumService topicForumService;


    public void register(String topicName) throws JMSException {
        ConsumerMessageListener listener = new ConsumerMessageListener(name,userMessages);
        topicForumService.registerConsumer(topicName, listener);
    }

    public void performSub(String topicName) throws JMSException {
        register(topicName);
        switch(isSubscribed){
            case "Unsubscribed":
                isSubscribed = "Subscribed";
                buttonValue = "Unsubscribe";
                break;
            case "Subscribed":
                isSubscribed = "Unsubscribed";
                buttonValue = "Subscribe";
                break;
            default:
                isSubscribed = "error!";
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

    public String getIsSubscribed() {
        return isSubscribed;
    }

    public void setIsSubscribed(String isSubscribed) {
        this.isSubscribed = isSubscribed;
    }

    public String getButtonValue() {
        return buttonValue;
    }

    public void setButtonValue(String buttonValue) {
        this.buttonValue = buttonValue;
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
}
