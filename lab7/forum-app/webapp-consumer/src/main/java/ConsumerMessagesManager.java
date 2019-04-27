import javax.annotation.Resource;
import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;


public class ConsumerMessagesManager {
    List<Topic> topics = new LinkedList<>();
    List<TopicSubscriber> subsribedTopics = new ArrayList<>();

    public TopicSubscriber registerConsumer(String topicName, ConsumerMessageListener listener) throws JMSException {
        System.out.println("Registering consumer: " + listener.getConsumerName() + " to " + topicName);
        final Properties initialContextProperties = new Properties();

        final String factory = "java:/ConnectionFactory";

        InitialContext ic = null;
        QueueConnectionFactory cf =null;
        try {
            ic = new InitialContext(initialContextProperties);
            cf = (QueueConnectionFactory) ic.lookup(factory);

        } catch (NamingException e) {
            e.printStackTrace();
        }


        Connection connection = cf.createConnection();
        connection.setClientID(listener.getConsumerName()+topicName);
        TopicSession topicSession = (TopicSession) connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        TopicSubscriber consumer = null;
        for (Topic topic : topics) {
            System.out.println(topic.getTopicName());
            if (topic.getTopicName().equals(topicName)) {
                System.out.println("Found topic: " + topicName);
                consumer = topicSession.createDurableSubscriber(topic, listener.getConsumerName()+topicName);
                System.out.println("Consumer: "+listener.getConsumerName()+" subscribed " +topicName +" successfully!");
                consumer.setMessageListener(listener);
                subsribedTopics.add(consumer);
                connection.start();
            }
        }
        return consumer;
    }

//    public void unRegisterConsumer(String topicName, String consumerName) throws JMSException {
//        System.out.println("Unregistering consumer: " + consumerName + " from topic " + topicName);
//        Connection connection = cf.createConnection();
//        TopicSession topicSession = (TopicSession) connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//
//        topicSession.unsubscribe(consumerName+topicName);
//
//    }
}
