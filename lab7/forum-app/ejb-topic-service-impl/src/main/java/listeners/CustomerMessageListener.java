package listeners;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.Arrays;

public class CustomerMessageListener implements MessageListener {

    private String name;

    public CustomerMessageListener(String name) {
        this.name = name;
    }

    @Override
    public void onMessage(Message m) {
        MapMessage mapMessage = (MapMessage) m;
        try {
            String topic = mapMessage.getString("topic");
            String message = mapMessage.getString("message");
            String subscribers = mapMessage.getString("subscribers");

            if (shouldReceiveMessage(subscribers)) {
                System.out.println("Received by " + name + ": " + topic + " -> " + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean shouldReceiveMessage(String subscribers) {
        return isEmpty(subscribers) || Arrays
                .stream(subscribers.split(","))
                .anyMatch(s -> s.equals(name));
    }

    private boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }
}
