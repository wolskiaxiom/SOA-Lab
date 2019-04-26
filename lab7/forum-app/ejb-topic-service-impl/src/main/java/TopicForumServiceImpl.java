import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jms.*;
import java.util.*;

@Stateless
@Remote(TopicForumService.class)
public class TopicForumServiceImpl implements TopicForumService {


    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory cf;

    @Resource(mappedName = "java:/jms/topic/SOA_test")
    private Topic jmsTopic;

    private Map<String, Set<String>> topics = new HashMap<>();



    @Override
    public void saveTopic(String topic) {
        topics.put(topic, new HashSet<>());
    }

    @Override
    public List<String> findAllTopics() {
        return new LinkedList<>(topics.keySet());
    }

    @Override
    public void sendMessage(String topic, String message, String subscribers) throws JMSException {
        Connection con = null;
        try {
            con = cf.createConnection();
            Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer publisher = session.createProducer(jmsTopic);

            con.start();

            MapMessage mapMessage = session.createMapMessage();
            mapMessage.setString("topic", topic);
            mapMessage.setString("message", message);
            mapMessage.setString("subscribers", subscribers);

            publisher.send(mapMessage);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (JMSException ignored) {
                }
            }
        }
    }
}
