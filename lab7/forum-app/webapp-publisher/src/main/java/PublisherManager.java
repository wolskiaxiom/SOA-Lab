import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.jms.JMSException;
import java.util.List;

//@ManagedBean(name = "publisher")
@Named
public class PublisherManager {

    @EJB(lookup="java:global/ejb-topic-service-impl-1.0-SNAPSHOT/TopicForumServiceImpl")
    private TopicForumService topicForumService;

    private String newTopicName;
    private String message;
    private String subscribers;

    public void addTopic() {
        topicForumService.saveTopic(newTopicName);
    }

    public void sendMessage(String topic) throws JMSException {
        topicForumService.sendMessage(topic, message, subscribers);
    }

    public List<String> getTopics() {
        return topicForumService.findAllTopics();
    }

    public String getNewTopicName() {
        return newTopicName;
    }

    public void setNewTopicName(String newTopicName) {
        this.newTopicName = newTopicName;
    }

    public TopicForumService getTopicService() {
        return topicForumService;
    }

    public void setTopicService(TopicForumService topicService) {
        this.topicForumService = topicService;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(String subscribers) {
        this.subscribers = subscribers;
    }

    public PublisherManager(){}

}
