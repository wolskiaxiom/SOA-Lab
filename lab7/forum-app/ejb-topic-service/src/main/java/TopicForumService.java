import javax.ejb.Local;
import javax.jms.JMSException;
import java.util.List;

@Local
public interface TopicForumService {
    void saveTopic(String topic);
    List<String> findAllTopics();
    void sendMessage(String topic, String message, String subscribers) throws JMSException;

}
