import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class UserMessages implements Serializable {

    private ArrayList<String> messages = new ArrayList<String>(Arrays.asList("Welcome in ForRum!\n"));

    public void addMessage(String message){
        messages.add(message);
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }
}
