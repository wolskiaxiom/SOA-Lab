import javax.ejb.Singleton;
import javax.ejb.Stateful;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

//@Singleton
@Stateful
public class MyMessageStorageImpl implements MyMessagesStorage, Serializable {

    private HashMap<String, ArrayList<String>> userMessageMap;

    @Override
    public ArrayList<String> getMessages(String user) {
        if(userMessageMap.containsKey(user)){
            return userMessageMap.get(user);
        }
        return new ArrayList<>();
    }

    @Override
    public void addMessage(String user, String message) {
        userMessageMap.get(user).add(message);
    }

    @Override
    public void setMessages(HashMap messages) {
        userMessageMap = messages;
    }
}
