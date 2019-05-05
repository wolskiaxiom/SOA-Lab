import javax.ejb.Remote;
import javax.jms.MapMessage;
import java.util.ArrayList;
import java.util.HashMap;

@Remote
public interface MyMessagesStorage {

    ArrayList<String> getMessages(String user);
    public void addMessage(String user, String message);
    public void setMessages(HashMap messages);


}
