package beans.jmsbeans;

import javax.ejb.Singleton;
import java.io.Serializable;
import java.util.ArrayList;

@Singleton
public class MyMessageStorage implements Serializable {
    private ArrayList<String> messages = new ArrayList<>();

    public MyMessageStorage() {
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }

    public void addMessage(String message){
        messages.add(message);
    }
}
