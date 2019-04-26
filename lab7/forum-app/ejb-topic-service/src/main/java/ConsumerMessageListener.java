import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.Serializable;

public class ConsumerMessageListener  implements MessageListener, Serializable {
    private String consumerName;
    private UserMessages allMessages;
    public ConsumerMessageListener(String consumerName, UserMessages messages) {
        this.consumerName = consumerName;
        allMessages = messages;
    }

    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            allMessages.addMessage(consumerName + " received " + textMessage.getText());
            System.out.println(allMessages.getMessages());
            System.out.println(consumerName + " received " + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public String getConsumerName() {
        return consumerName;
    }

    public UserMessages getAllMessages() {
        return allMessages;
    }

    public void setAllMessages(UserMessages allMessages) {
        this.allMessages = allMessages;
    }
}