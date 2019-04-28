package beans.jmsbeans;

import javax.ejb.Singleton;
import javax.jms.TextMessage;
import java.io.Serializable;
import java.util.ArrayList;

@Singleton
public class MyMessageStorage implements Serializable {
    private ArrayList<TextMessage> messages = new ArrayList<>();

    public MyMessageStorage() {
    }

    public ArrayList<TextMessage> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<TextMessage> messages) {
        this.messages = messages;
    }

    public void addMessage(TextMessage message){
        messages.add(message);
    }
}
