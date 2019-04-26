import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.jms.Topic;
import java.util.LinkedList;
import java.util.List;

@Named
@ApplicationScoped
public class PublisherManager {

    @EJB(lookup="java:global/ejb-topic-service-impl-1.0-SNAPSHOT/TopicForumServiceImpl")
    private TopicForumService topicForumService;

    private String newTopicName;
    private String message;
    private String subscribers;

    public void addTopic() throws JMSException {
        topicForumService.saveTopic(newTopicName);
    }

    public void sendMessage(String topic) throws JMSException {
        topicForumService.sendMessage(topic, message, subscribers);
    }

    public List<String> getTopics() throws JMSException {
        List<String> topics = new LinkedList<>();
        for(Topic topic: topicForumService.findAllTopics()){
            topics.add(topic.getTopicName());
        }
        return topics;
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
