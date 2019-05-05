import javax.ejb.EJB;
import javax.jms.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class ConsumerMessageListener implements MessageListener, Serializable {
    private String consumerName;

    @EJB(lookup="java:global/ejb-topic-service-impl-1.0-SNAPSHOT/MyMessageStorageImpl")
    private MyMessagesStorage myMessagesStorage;

    private UserMessages userMessages = new UserMessages();

    public ArrayList<String> getMessages(){
        return userMessages.getMessages();
    }

    public ConsumerMessageListener(String consumerName){
        this.consumerName = consumerName;
    }

    public void onMessage(Message m) {
        try {
            MapMessage mapMessage = (MapMessage) m;
            String message = mapMessage.getString("message");
            String subscribers = mapMessage.getString("subscribers");
            if (shouldReceiveMessage(subscribers)) {
//                myMessagesStorage.addMessage(consumerName, message);
//                userMessages.addMessage(message);
                System.out.println("Consumer " + consumerName +" received message: " +message);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public String getConsumerName() {
        return consumerName;

    }

    private boolean shouldReceiveMessage(String subscribers) {
        return subscribers.isEmpty()
                || subscribers.trim().length() == 0
                || Arrays
                .stream(subscribers.split(","))
                .anyMatch(s -> s.equals(consumerName));
    }

}