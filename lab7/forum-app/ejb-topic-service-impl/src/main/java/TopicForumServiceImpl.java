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

    List<Topic> topics = new LinkedList<>();


    @Override
    public void registerConsumer(String topicName, ConsumerMessageListener listener) throws JMSException {
        System.out.println("Registering consumer: " + listener.getConsumerName());
        Connection connection = cf.createConnection();
        TopicSession topicSession = (TopicSession) connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        for (Topic topic: topics) {
            System.out.println(topic.getTopicName());
            if(topic.getTopicName().equals(topicName)){
                MessageConsumer consumer = topicSession.createSubscriber(topic);
                consumer.setMessageListener(listener);
                connection.start();
            }
        }
    }


    @Override
    public void saveTopic(String topic) throws JMSException {
        Connection connection = cf.createConnection();
        TopicSession topicSession = (TopicSession) connection.createSession();
        Topic newTopic = topicSession.createTopic(topic);
        topics.add(newTopic);
        connection.close();
    }

    @Override
    public List<Topic> findAllTopics() {
        return topics;
    }

    private Topic getJmsTopic(String topicName) throws JMSException {
        for (Topic topic: topics) {
            System.out.println(topic.getTopicName());
            if(topic.getTopicName().equals(topicName)){
                return topic;
            }
        }
        return null;
    }

    @Override
    public void sendMessage(String topicName, String message, String subscribers) throws JMSException {
        Connection connection = null;
        try {
            connection = cf.createConnection();
            TopicSession topicSession = (TopicSession) connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Message msg = topicSession.createTextMessage(message);

            TopicPublisher publisher = topicSession.createPublisher(getJmsTopic(topicName));
            System.out.println("Sending text" + message);
            publisher.publish(msg);
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException ignored) {
                }
            }
        }
    }
}
