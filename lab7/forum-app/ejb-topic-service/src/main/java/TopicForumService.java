import javax.ejb.Remote;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Topic;
import java.util.List;

@Remote
public interface TopicForumService {
    void registerConsumer(String topicName, ConsumerMessageListener listener) throws JMSException;

    void saveTopic(String topic) throws JMSException;
    List<Topic> findAllTopics();
    void sendMessage(String topic, String message, String subscribers) throws JMSException;

}
