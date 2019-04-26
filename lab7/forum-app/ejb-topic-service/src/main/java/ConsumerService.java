import javax.ejb.Remote;
import javax.jms.JMSException;

@Remote
public interface ConsumerService {
    void registerConsumer(String name) throws JMSException;
}
